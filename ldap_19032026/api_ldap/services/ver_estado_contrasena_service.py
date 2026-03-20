from django.conf import settings
from django.http import JsonResponse
from rest_framework import status
from .ldap_service import LdapService
from utils.generic_response_serealizer import GenericResponseSerializer
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import SUBTREE 
from ldap3.utils.conv import escape_filter_chars

# Configuración de logging con formato personalizado para quitar INFO:root
#logging.basicConfig(level=logging.INFO, format='%(message)s') # Solo mostrar el mensaje

def ver_estado_contrasena(username): 
    conn = None
    conn_user = None
    try:
        conn = ConexionLdap.conexion_ldap()
        usuario = escape_filter_chars(username)
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        forzar_cambio = False
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")

        entry = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        if not conn.entries:
            return ResponseService._build_error_response(
                    code=404,
                    message="Usuario NO Encontrado",
                    data={"existe": False}
                )
        else:
            pwd_last_set = int(entry.entry_raw_attributes['pwdLastSet'][0])

            if pwd_last_set == 0:
                msg="El usuario está obligado a cambiar su contraseña en el próximo inicio de sesión.",
                forzar_cambio = True
            else:
                msg = "El usuario ya tiene una contraseña establecida."
            return ResponseService._build_success_response(
                    message=msg,
                    data={
                        "usuario": username,
                        "forzarCambio": forzar_cambio
                    }
            )
        
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(500, e)

    finally:
        if conn_user:
            conn_user.unbind()
        if conn:
            ConexionLdap.desvincular_conexion_ldap(conn)