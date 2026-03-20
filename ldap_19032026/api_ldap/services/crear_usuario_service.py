from django.conf import settings

from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
from ldap3 import MODIFY_ADD, MODIFY_REPLACE
from .ldap_service import LdapService
from datetime import datetime
import pytz

def crear_usuario_service(data):
    try:
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS","Default Value")
        usuario = data.get('usuario')
        __validarUsuarioCreate(conn, data, usuario)

        ubicacion = data.get('ubicacion')
        dn = f"CN={data.get('nombre')} {data.get('apellido')},{ubicacion}"

        user_attributes = __mapear_usuario(data)
        if data.get('fechaExpiracion'):
            expiration_filetime = LdapService.procesar_fecha(data.get('fechaExpiracion'))
            user_attributes['accountExpires'] = expiration_filetime

        conn.add(dn, attributes=user_attributes)
        if conn.result['result'] not in (0, 68):
            raise ValidationError(conn.result.get('message'))
        if conn.result['result'] == 68:
            raise ValidationError(f'La combinación del nombre y apellido ya existe.')
        LdapService.obligarCambioContrasena(conn, dn)
        LdapService.habilitarCuenta(conn, dn)
        LdapService.agregarNivelInternet(conn, dn, ldapBinddns)
        mail = f"{data.get('usuario')}@economiayfinanzas.gob.bo"

        result_data = {
            "usuario": data.get("usuario"),
            "nombre": data.get("nombre"),
            "apellido": data.get("apellido"),
            "cedulaIdentidad": data.get("cedulaIdentidad"),
            "cargo": data.get("cargo"),
            "mail": mail
        }
        return ResponseService._build_success_response(
            #statusCode: 201,
            message="Usuario creado correctamente.",
            data=result_data
        )

    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(400, e)

    finally:
        ConexionLdap.desvincular_conexion_ldap(conn)


def __mapear_usuario(data):
    mail = f"{data.get('usuario')}@economiayfinanzas.gob.bo"
    user_attributes = {
    'objectClass': ['top', 'person', 'organizationalPerson', 'user'],
    'sAMAccountName': data.get('usuario'),
    #'userPrincipalName': f"{data.get('usuario')}@evirtual.economiayfinanzas.gob.bo",
    'givenName': data.get('nombre'),
    'sn': data.get('apellido'),
    'cn': f"{data.get('nombre')} {data.get('apellido')}",
    'displayName': f"{data.get('nombre')} {data.get('apellido')}",
    'mail': mail,
    'title': data.get('cargo'),
    'department': data.get('departamento'),
    'pager': data.get('cedulaIdentidad'),
    'company': 'Ministerio de Economia y Finanzas Publicas',
    'userAccountControl': 514,
    'physicalDeliveryOfficeName':data.get('piso'),
    'ipPhone':data.get('interno'), # Cuenta deshabilitada,
    }
    password_utf16 = LdapService.generarPassword()
    user_attributes['unicodePwd'] = [password_utf16]
    return user_attributes

def __validarUsuarioCreate(conn, data, usuario):
    ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
    #ubicacion = data.get('ubicacion') +','+ubicacionBase
    ubicacion = data.get('ubicacion')
    ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
    ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")
    usuario_info = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral)    

    if usuario_info:
        raise ValidationError(f"El usuario {data.get('usuario')} ya existe.")
    conn.search(ldapBinddns, f'(distinguishedName={ubicacion})', attributes=['distinguishedName', 'sAMAccountName'])
    if not conn.entries:
        raise ValidationError(f"La ubicación {ubicacion} no existe en el directorio.")
