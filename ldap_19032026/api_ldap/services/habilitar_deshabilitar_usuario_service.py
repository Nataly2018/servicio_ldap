from django.conf import settings

from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import Server, Connection, Tls, MODIFY_REPLACE, SUBTREE
from ldap3.core.exceptions import LDAPException
from .ldap_service import LdapService

def habilitar_o_deshabilitar_usuario_service(data):
    try:
        """if not usuario:
            raise ValidationError("El campo 'usuario' es obligatorio y no puede estar vacío.") """
        usuario = data.get("usuario")
        habilitar = data.get("habilitar")
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        entry = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        
        if entry:
            usuario_info = entry
            user_dn = usuario_info.entry_dn  

            user_account_control = usuario_info.userAccountControl.value

            # Verificar si el usuario ya está habilitado o deshabilitado según el valor de habilitar
            if habilitar:
                if user_account_control & 2 == 0:  
                    raise ValidationError(f"El usuario '{usuario}' ya está habilitado.", code="user_already_enabled")
                # Habilitar usuario (Desactivar el bit 2)
                new_user_account_control = int(user_account_control) & ~2
            else:
                if user_account_control & 2 != 0:  
                    raise ValidationError(f"El usuario '{usuario}' ya está deshabilitado.", code="user_already_disabled")
                # Deshabilitar usuario (Activar el bit 2)
                new_user_account_control = int(user_account_control) | 2

            # Modificar el atributo userAccountControl
            conn.modify(user_dn, {"userAccountControl": [(MODIFY_REPLACE, [new_user_account_control])]})

            if conn.result["result"] == 0:
                return ResponseService._build_success_response(
                  message=f"Usuario '{usuario}' {'habilitado' if habilitar else 'deshabilitado'} exitosamente.",
                  data={"usuario": usuario, "habilitado": data.get('habilitar')}
                )
            else:
                raise ValidationError("Comuniquese con el administrador")
        
        else:
            raise ValidationError(f"El usuario '{usuario}' no existe.")
    
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except LDAPException as e:
        raise ValidationError(f"Error en la autenticación LDAP: {str(e)}")
    except Exception as e:
        raise ValidationError(f"Error: {str(e)}")
    
    finally:
        if 'conn' in locals():
            ConexionLdap.desvincular_conexion_ldap(conn)