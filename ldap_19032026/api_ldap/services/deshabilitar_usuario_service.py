from django.conf import settings
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import Server, Connection, Tls, MODIFY_REPLACE,MODIFY_ADD, SUBTREE
from ldap3.core.exceptions import LDAPException
from datetime import datetime, timezone
from .ldap_service import LdapService

def deshabilitar_y_mover_usuario_service(data):
    try:
        usuario = data.get("usuario")

        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        ldapBinddnsDisable2025 = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")

        entry = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        #usuario_info = buscar_usuario(conn, ldapBinddns, usuario)
        user_dn = entry.entry_dn
        display_name = entry.displayName.value
        user_account_control = entry.userAccountControl.value
        ou_usuario = LdapService.obtener_ou_usuario(conn, usuario)
        fecha_actual = datetime.now().strftime("%Y-%m-%d")
        LdapService.deshabilitar_usuario(conn, user_dn, user_account_control, usuario)
        LdapService.actualizar_expiracion_usuario(conn, entry, usuario, fecha_actual,ou_usuario)
        nueva_ou = ldapBinddnsDisable2025
        nuevo_dn = LdapService.mover_usuario(conn, user_dn, display_name, nueva_ou)

        return ResponseService._build_success_response(
            message=f"Usuario '{usuario}' deshabilitado y movido correctamente.",
            data={"usuario": usuario, "nuevo_dn": nuevo_dn}
        )

    except ValidationError as e:
        return ResponseService._build_error_response(400, e)

    except LDAPException as e:
        raise ValidationError(f"Error LDAP: {str(e)}")

    except Exception as e:
        raise ValidationError(f"Error inesperado: {str(e)}")

    finally:
        if 'conn' in locals():
            ConexionLdap.desvincular_conexion_ldap(conn)




