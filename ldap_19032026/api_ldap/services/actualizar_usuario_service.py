from django.conf import settings

from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from .ldap_service import LdapService
from django.core.exceptions import ValidationError
from ldap3 import MODIFY_REPLACE
from datetime import datetime
import pytz
import logging

logger = logging.getLogger(__name__)

def actualizar_usuario_service(data):
    try:
        conn = ConexionLdap.conexion_ldap()
        usuario = data.get('usuario')
        ldap_bind = getattr(settings, "LDAP_BINDDNS", "DC=ejemplo,DC=com")
        ldapBinddnsDisable = getattr(settings, "LDAP_BINDDNS_DISABLE_25", "Default Value")
        ldapBinddnsGeneral = getattr(settings, "LDAP_BINDDNS_DISABLE", "Default Value")

        entry = LdapService.verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldap_bind, ldapBinddnsGeneral)
        user_dn = entry.entry_dn
        if not user_dn:
            raise ValidationError(f"El usuario {usuario} no existe en las UO deshabilitados y evirtual, comuniquese con infraestructura.")
        
        current_accountExpires = entry.accountExpires.value

        # Preparar las actualizaciones de atributos
        updates = {}
        if "nombre" in data and data.get("nombre"):
            updates["givenName"] = [(MODIFY_REPLACE, [data.get("nombre")])]
        if "apellido" in data and data.get("apellido"):
            updates["sn"] = [(MODIFY_REPLACE, [data.get("apellido")])]
        if "cargo" in data and data.get("cargo"):
            updates["title"] = [(MODIFY_REPLACE, [data.get("cargo")])]
        if "departamento" in data and data.get("departamento"):
            updates["department"] = [(MODIFY_REPLACE, [data.get("departamento")])]
        if "cedulaIdentidad" in data and data.get("cedulaIdentidad"):
            updates["pager"] = [(MODIFY_REPLACE, [data.get("cedulaIdentidad")])]
        if "interno" in data and data.get("interno"):
            updates["ipPhone"] = [(MODIFY_REPLACE, [data.get("interno")])]
        if "piso" in data and data.get("piso"):
            updates["physicalDeliveryOfficeName"] = [(MODIFY_REPLACE, [data.get("piso")])]
        if "nuevoNombreUsuario" in data and data.get("nuevoNombreUsuario"):
            updates["sAMAccountName"] = [(MODIFY_REPLACE, [data.get("nuevoNombreUsuario")])]

        # Si se envía 'fecha_expiracion', se actualiza la fecha de expiración
        if "fechaExpiracion" in data and data.get("fechaExpiracion"):
            fecha_expiracion = data.get("fechaExpiracion")
            try:
                # Asegurarse de que la fecha esté en el formato correcto
                expiration_date = datetime.strptime(fecha_expiracion, "%Y-%m-%d")
                local_tz = pytz.timezone('America/Bogota')
                local_date = local_tz.localize(expiration_date)
                utc_date = local_date.astimezone(pytz.utc)
                expiration_time = int(utc_date.timestamp())
                expiration_filetime = expiration_time * 10000000 + 116444736000000000
                # Ajuste de 1 día (en unidades de 100 nanosegundos)
                expiration_filetime += 864000000000
            except ValueError:
                raise ValidationError(f"Formato de fecha expiración no válido: {fecha_expiracion}")

            # Si la fecha de expiración es válida, se actualiza
            updates["accountExpires"] = [(MODIFY_REPLACE, [expiration_filetime])]
        else:
            # Si no se pasa fecha_expiracion, se elimina la fecha de expiración (establece en 0)
            if current_accountExpires != 0:
                updates["accountExpires"] = [(MODIFY_REPLACE, [0])]

        # Realizar la actualización en AD
        if updates:
            conn.modify(user_dn, updates)
            if conn.result["result"] != 0:
                raise ValidationError(f"Error al actualizar los datos: {conn.result['description']}")
        else:
            raise ValidationError("No se proporcionaron datos válidos para actualizar.")

        # Si se envía 'nueva_ubicacion', mover el usuario
        """
        if "nuevaUbicacion" in data and data.get("nuevaUbicacion"):
            nueva_ubicacion = data.get("nuevaUbicacion")
            display_name = entry.displayName.value
            LdapService.validar_ou_existe(conn, nueva_ubicacion)
            LdapService.mover_usuario(conn, user_dn, display_name,nueva_ubicacion)
        """
        return ResponseService._build_success_response(
            message=f"Usuario '{usuario}' actualizado exitosamente"
                )
    except ValidationError as e:
        return ResponseService._build_error_response(400, e)
    except Exception as e:
        return ResponseService._build_error_response(500, e)
    finally:
        ConexionLdap.desvincular_conexion_ldap(conn)
