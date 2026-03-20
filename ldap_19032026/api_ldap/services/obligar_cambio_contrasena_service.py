from django.conf import settings
from django.http import JsonResponse
from rest_framework import status
from utils.generic_response_serealizer import GenericResponseSerializer
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import SUBTREE 
from ldap3.utils.conv import escape_filter_chars
from ldap3 import MODIFY_REPLACE
from .ldap_service import LdapService

# Configuración de logging con formato personalizado para quitar INFO:root
#logging.basicConfig(level=logging.INFO, format='%(message)s') # Solo mostrar el mensaje

def obligar_cambio_contrasena(usuario):
    conn = ConexionLdap.conexion_ldap()
    safe_usuario = escape_filter_chars(usuario)
    ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
    ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
    ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
    entry = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)

    if not conn.entries:
        return ResponseService._build_error_response(
            code=404,
            message="Usuario NO Encontrado",
            data={"existe": False}
        )

    # Tomar el DN del usuario encontrado
    entry = conn.entries[0]
    user_dn = entry.distinguishedName.value

    try:
        conn.modify(user_dn, {'pwdLastSet': [(MODIFY_REPLACE, [0])]})

        if conn.result['result'] == 0:
            msg = f"El usuario {usuario} ha sido configurado para cambiar su contraseña en el próximo inicio de sesión."
            success = True
        else:
            msg = f"No se pudo actualizar el usuario {usuario}: {conn.result['description']}"
            success = False
    except Exception as e:
        msg = f"Error actualizando usuario {usuario}: {str(e)}"
        success = False

    ConexionLdap.desvincular_conexion_ldap(conn)
    if success:
        return ResponseService._build_success_response(message=msg, data={"usuario": usuario})
    else:
        return ResponseService._build_error_response(code=500, message=msg, data={"usuario": usuario})
