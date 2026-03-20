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

def existe_usuario_service(usuario): 
    conn = None
    conn_user = None
    try:
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
        valor = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        if valor:
            return ResponseService._build_success_response(message="Usuario Encontrado")
        else:
            return ResponseService._build_error_response(400, message="Usuario No Encontrado")
        
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(500, e)
    finally:
        if conn_user:
            conn_user.unbind()
        if conn:
            ConexionLdap.desvincular_conexion_ldap(conn)
