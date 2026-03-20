from django.conf import settings

from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import Server, Connection, Tls, MODIFY_REPLACE, SUBTREE
from ldap3.core.exceptions import LDAPException
from .ldap_service import LdapService

def estado_usuario_service(data):
    try:
        """if not usuario:
            raise ValidationError("El campo 'usuario' es obligatorio y no puede estar vacío.") """
        usuario = data.get("usuario")
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")

        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")

        usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        user_account_control = usuario_info.userAccountControl.value

        # Verificar si el usuario ya está habilitado o deshabilitado según el valor de habilitar
        estado=False
        if user_account_control & 2 == 0:
            estado=True
        else:
            estado=False
        return ResponseService._build_success_response(
            message=f"Usuario '{usuario}' {'habilitado' if estado else 'deshabilitado'} .",
            data={"estado": estado}
            )
    
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except LDAPException as e:
        raise ValidationError(f"Error en la autenticación LDAP: {str(e)}")
    except Exception as e:
        raise ValidationError(f"Error: {str(e)}")
    
    finally:
        if 'conn' in locals():
            ConexionLdap.desvincular_conexion_ldap(conn)