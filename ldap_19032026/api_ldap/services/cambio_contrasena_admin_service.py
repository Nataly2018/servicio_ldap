from django.conf import settings
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import MODIFY_REPLACE, SUBTREE
from datetime import datetime, timedelta, timezone
from zoneinfo import ZoneInfo
from .ldap_service import LdapService

def cambio_contrasena_admin_service(data):
    try:
        es_admin = data.get("esAdmin")
        usuario = data.get("usuario")
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")

        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)

        LdapService.validar_fecha_caducidad_cuenta(usuario_info)
        LdapService.validar_usuario_sin_cambio_contrasena(usuario_info)

        user_dn = usuario_info.entry_dn

        # Formatear la nueva contraseña
        new_password_formatted = f'"{data.get("nuevaContrasena")}"'.encode("utf-16-le")

        conn.modify(user_dn, {"unicodePwd": [(MODIFY_REPLACE, [new_password_formatted])]})
        if es_admin:
            conn.modify(user_dn, {'pwdLastSet': [(MODIFY_REPLACE, [0])]})
        return ResponseService._build_success_response(message="Cambio de Contraseña Exitoso")
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(500, e)
    finally:
        if conn:
            ConexionLdap.desvincular_conexion_ldap(conn)

