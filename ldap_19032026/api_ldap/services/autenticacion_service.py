from zoneinfo import ZoneInfo
from ldap3 import Connection, SUBTREE
from ldap3 import BASE
from django.core.exceptions import ValidationError

from api_ldap.services.conexion_ldap_service import ConexionLdap
from backend_ldap_python import settings
from datetime import datetime, timedelta, timezone
from .ldap_service import LdapService

from utils.responses import ResponseService

def autenticacion_service(data):
    conn = None
    conn_user = None
    try:
        usuario = data.get("usuario")
        password = data.get("contrasena")
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")

        usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)
        
        LdapService.validar_fecha_caducidad_cuenta(usuario_info)
        LdapService.validar_usuario(usuario_info)
        
        
        dominio_dn = ",".join([x for x in ldapBinddns.split(",") if x.strip().startswith("DC=")]);
        conn.search(
        search_base=dominio_dn,
        search_filter="(objectClass=domain)",
        search_scope=SUBTREE,
        attributes=["maxPwdAge"]
        )

        if not conn.entries:
            raise ValidationError("No se pudo obtener la política de contraseñas del dominio.")

        user_dn = usuario_info.entry_dn
        conn_user = Connection(conn.server, user=user_dn, password=password, auto_bind=False)

        if conn_user.bind():
            return ResponseService._build_success_response(
                  message="Autenticación Exitosa"
            )
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

