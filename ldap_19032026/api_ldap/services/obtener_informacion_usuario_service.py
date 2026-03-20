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
from .ldap_service import LdapService

# Configuración de logging con formato personalizado para quitar INFO:root
#logging.basicConfig(level=logging.INFO, format='%(message)s') # Solo mostrar el mensaje

def obtener_informacion_usuario_service(usuario): 
    conn = None
    conn_user = None
    try:
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        if not usuario_info:
            raise ValidationError(f"El usuario {usuario} no existe.")
        LdapService.validar_fecha_caducidad_cuenta(usuario_info)
        # Búsqueda de usuario en AD por SamAccountName

        if len(conn.entries) > 0:
            usuario_info = conn.entries[0]
            # Información básica del usuario
            ubicacion = usuario_info.entry_dn.split(',', 1) if getattr(usuario_info, "entry_dn", None) else ""
            piso = usuario_info["physicalDeliveryOfficeName"].value if "physicalDeliveryOfficeName" in usuario_info else ""
            interno = usuario_info["ipPhone"].value if "ipPhone" in usuario_info else ""
            departamento = usuario_info["department"].value if "department" in usuario_info else ""
            cargo = usuario_info["title"].value if "title" in usuario_info else ""
            display_name = usuario_info["displayName"].value if "displayName" in usuario_info else ""
            email = usuario_info["mail"].value if "mail" in usuario_info else ""
            pwd_last_set = int(usuario_info.entry_raw_attributes['pwdLastSet'][0])
            requiereCambioContrasena = (pwd_last_set == 0)
            ci = usuario_info["pager"].value if "pager" in usuario_info else ""
            # Comprobar si la cuenta está habilitada o deshabilitada
            account_enabled = (usuario_info.userAccountControl.value & 2) == 0  # 0 si está habilitada, True si deshabilitada
            dominio_dn = ",".join([x for x in ldapBinddns.split(",") if x.strip().startswith("DC=")])
            conn.search(
            search_base=dominio_dn,
            search_filter="(objectClass=domain)",
            search_scope=SUBTREE,
            attributes=["maxPwdAge"]
            )

            if not conn.entries:
                raise ValidationError("No se pudo obtener la política de contraseñas del dominio.")

            politica_dominio = conn.entries[0]
            fecha_expiracion = LdapService.obtener_politica_expiracion_contrasena(politica_dominio, usuario_info)
            # Imprimir la información relevante

            return ResponseService._build_success_response(
                        message="Usuario Encontrado",
                        data={
                            "usuario": usuario,
                            "nombre": display_name,
                            "email": email,
                            "habilitado": account_enabled,
                            "fechaExpiracionContrasena": fecha_expiracion,
                            "ubicacion": ubicacion[1],
                            "piso" : piso,
                            "interno" : interno,
                            "departamento" : departamento,
                            "cargo" : cargo,
                            "requiereCambioContrasena": requiereCambioContrasena,
                            "ci": ci
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



