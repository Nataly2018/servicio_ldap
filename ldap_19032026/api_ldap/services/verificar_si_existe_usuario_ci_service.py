from zoneinfo import ZoneInfo
from django.conf import settings
from django.http import JsonResponse
from rest_framework import status
from utils.generic_response_serealizer import GenericResponseSerializer
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import SUBTREE 
from datetime import datetime, timedelta, timezone
from api_ldap.services.ldap_service import LdapService
# Configuración de logging con formato personalizado para quitar INFO:root
#logging.basicConfig(level=logging.INFO, format='%(message)s') # Solo mostrar el mensaje

def verificar_existe_usuario_ci_service(data): 
    conn = None
    conn_user = None
    usuario = data.get("usuario")
    ci = data.get("ci")
    try:
        conn = ConexionLdap.conexion_ldap()
        ldapBinddnsDisable2025 = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable2025, ldapBinddns, ldapBinddnsGeneral)
        
        usuarioPorCi = verificar_existe_ci(ci, conn, ldapBinddns, ldapBinddnsDisable2025)

        if usuario_info and usuarioPorCi:
            ci = usuario_info["pager"].value if "pager" in usuario_info else ""
            ci = ci or ""
            if not ci.strip():
                raise ValidationError("El usuario existe pero no tiene un número de CI en el AD, comuniquese con el administrador")

            if usuarioPorCi["existe"] :
                if (usuario_info.pager == usuarioPorCi["ci"]):
                    return ResponseService._build_success_response(message="Usuario y cedula de identidad encontrados con el mismo usuario", data={"code": 101})
                raise ValidationError(message="Usuario y CI encontrados con diferentes usuarios")
            return ResponseService._build_success_response(message="Usuario encontrado con otro número de CI", data={"code": 102})
        else:
            if usuarioPorCi["existe"]:
                raise ValidationError("CI encontrado con otro usuario")
            else:
               if usuario_info:
                    raise ValidationError("Usuario encontrado sin número de CI")
               return ResponseService._build_success_response(message="Usuario no encontrado", data={"code": 100})

    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(500, e)
    finally:
        if conn_user:
            conn_user.unbind()
        if conn:
            ConexionLdap.desvincular_conexion_ldap(conn)

def verificar_existe_ci(ci, conn, ldapBinddns, ldapBinddnsDisable):
    # Filtro de búsqueda para el CI
    filtro = f'(pager={ci})'

    # Realizar la búsqueda en el directorio
    conn.search(
        search_base= ldapBinddns ,  # Aquí debes poner tu DN base
        search_filter=filtro,
        search_scope=SUBTREE,
        attributes=['cn', 'sAMAccountName', 'pager']  # Ajusta los atributos que quieras obtener
    )

    # Verificar si hay resultados
    if conn.entries:
        user = conn.entries[0]
        return {
            "existe": True,
            "cn": str(user.cn),
            "usuario": str(user.sAMAccountName),
            "ci": str(user.pager)
        }
    conn.search(
        search_base= ldapBinddnsDisable ,  # Aquí debes poner tu DN base
        search_filter=filtro,
        search_scope=SUBTREE,
        attributes=['cn', 'sAMAccountName', 'pager']  # Ajusta los atributos que quieras obtener
    )
    if conn.entries:
        user = conn.entries[0]
        return {
            "existe": True,
            "cn": str(user.cn),
            "usuario": str(user.sAMAccountName),
            "ci": str(user.pager)
    }
    return {
            "existe": False
    }
