from django.conf import settings
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import Server, Connection, Tls, MODIFY_REPLACE, SUBTREE
from ldap3.core.exceptions import LDAPException
from datetime import datetime, timedelta, timezone
from zoneinfo import ZoneInfo
from .ldap_service import LdapService


def cambiar_contrasena_usuario_service(data): 
    try:
        conn = ConexionLdap.conexion_ldap()
        conn_user = None
        usuario = data.get("usuario")
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")

        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)

        if data.get("nuevaContrasena") == data.get("contrasenaActual"):
            raise ValidationError("La nueva contraseña no puede ser la misma que la actual")
        usuario_info = conn.entries[0]

        LdapService.validar_fecha_caducidad_cuenta(usuario_info)
        LdapService.validar_usuario_sin_cambio_contrasena(usuario_info)

        user_dn = usuario_info.entry_dn
        # Limpiar y convertir a string la contraseña actual
        contrasena_actual = str(data.get("contrasenaActual")).strip()

        conn_user = Connection(
            conn.server,
            user=user_dn,
            password=contrasena_actual,
            auto_bind=False
        )

        if conn_user.bind():
            result_code = conn_user.result.get('result', None)
            # ✅ Contraseña válida → cambio directo
            new_password_formatted = f'"{data.get("nuevaContrasena")}"'.encode("utf-16-le")
            if conn.modify(user_dn, {"unicodePwd": [(MODIFY_REPLACE, [new_password_formatted])]}):
                return ResponseService._build_success_response(
                    message="Cambio de contraseña exitoso"
                )
            else:
                raise ValidationError("No se pudo cambiar la contraseña. Verifique políticas de seguridad.")

        else:
            result_code = conn_user.result.get('result', None)
            message = conn_user.result.get('message', '')

            # 49 = invalidCredentials
            # 773 = password must change
            if result_code == 49 and '773' in message:
                new_password_formatted = f'"{data.get("nuevaContrasena")}"'.encode("utf-16-le")
                # ✅ Contraseña correcta pero AD exige cambio obligatorio
                if conn.modify(user_dn, {"unicodePwd": [(MODIFY_REPLACE, [new_password_formatted])]}):
                    return ResponseService._build_success_response(
                        message="Cambio de contraseña exitoso"
                    )
                else:
                    raise ValidationError("No se pudo cambiar la contraseña. Verifique políticas de seguridad.")
            else:
                raise ValidationError("Usuario o contraseña incorrectos")
            
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(500, e)
    finally:
        if conn_user:
            conn_user.unbind()
        if conn:
            ConexionLdap.desvincular_conexion_ldap(conn)


