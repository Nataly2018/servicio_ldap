from ldap3 import SUBTREE, MODIFY_ADD, MODIFY_REPLACE
from django.core.exceptions import ValidationError
from datetime import datetime, timedelta, timezone
from zoneinfo import ZoneInfo
from ldap3 import Connection
import pytz
from datetime import datetime

from utils.responses import ResponseService
class LdapService:

    @staticmethod
    def procesar_fecha(fecha):
        try:
            # Convertir la fecha al formato esperado por LDAP (DD/MM/YYYY)
            fecha_expiracion = datetime.strptime(fecha, "%Y-%m-%d")
            # Localizar la fecha a la zona horaria correcta (por ejemplo, Bogotá)
            local_tz = pytz.timezone('America/Bogota')
            local_date = local_tz.localize(fecha_expiracion, is_dst=None)
            
            # Ajustar la hora para que no afecte al día siguiente
            local_date = local_date.replace(hour=23, minute=59, second=59, microsecond=999999)
            
            # Convertir a UTC
            utc_date = local_date.astimezone(pytz.utc)
            
            # Convertir la fecha UTC a un timestamp
            expiration_time = int(utc_date.timestamp())
            
            # Convertir el timestamp a formato LDAP (en 100 nanosegundos)
            expiration_filetime = expiration_time * 10000000 + 116444736000000000
            
            return expiration_filetime
        except ValueError:
            raise ValidationError(f"Formato de fecha de expiración inválido.")
        except Exception as e:
            raise ValidationError(f"Error al procesar la fecha de expiración: {e}")
    @staticmethod
    def verificar_si_existe_usuario(conn, usuario, ldapBinddnsDisable, ldapBinddns, ldapBinddnsGeneral):
        # Buscar primero en OU habilitados
        encontrado = conn.search(
            ldapBinddns,
            f'(sAMAccountName={usuario})',
            SUBTREE,
            attributes=['displayName', 'mail', 'userAccountControl', 'lockoutTime', 'pwdLastSet', 'accountExpires', 'info', 'physicalDeliveryOfficeName', 'ipPhone', 'department', 'title', 'pager']
        )

        if conn.entries:
            return conn.entries[0]  # ← devuelve directamente la entry
        
        # Si no encuentra, buscar en OU deshabilitados
        encontrado = conn.search(
            ldapBinddnsDisable,
            f'(sAMAccountName={usuario})',
            SUBTREE,
            attributes=['displayName', 'mail', 'userAccountControl', 'lockoutTime', 'pwdLastSet', 'accountExpires', 'info']
        )

        if conn.entries:
            return conn.entries[0]
        # Si no encuentra, buscar en OU deshabilitados
        encontrado = conn.search(
            ldapBinddnsGeneral,
            f'(sAMAccountName={usuario})',
            SUBTREE,
            attributes=['displayName', 'mail', 'userAccountControl', 'lockoutTime', 'pwdLastSet', 'accountExpires', 'info']
        )

        if conn.entries:
            return conn.entries[0]
    
    @staticmethod
    def validar_fecha_caducidad_cuenta(usuario_info):
        account_expires_raw = int(usuario_info.entry_raw_attributes.get('accountExpires', [0])[0])
        if account_expires_raw not in (0, 9223372036854775807):
            expires_dt_utc = datetime(1601, 1, 1, tzinfo=timezone.utc) + timedelta(microseconds=account_expires_raw / 10)
            expires_dt_local = expires_dt_utc.astimezone(ZoneInfo("America/La_Paz"))
            now_local = datetime.now(tz=ZoneInfo("America/La_Paz"))
            if expires_dt_local < now_local:
                fecha_local = expires_dt_local.date()
                raise ValidationError("La cuenta de usuario a expirado")
            
    @staticmethod
    def obtener_politica_expiracion_contrasena(politica_dominio, usuario_info):
        try:
            max_pwd_age = int(politica_dominio.entry_raw_attributes['maxPwdAge'][0])
            pwd_last_set = int(usuario_info.entry_raw_attributes['pwdLastSet'][0])
            pwd_last_set_dt = datetime(1601, 1, 1, tzinfo=timezone.utc) + timedelta(seconds=pwd_last_set / 10_000_000)
            dias_expira = abs(max_pwd_age) / 10_000_000 / 60 / 60 / 24
            #fecha_expiracion = pwd_last_set_dt + timedelta(days=dias_expira)
            fecha_expiracion_utc = pwd_last_set_dt + timedelta(days=dias_expira)
            tz_la_paz = ZoneInfo("America/La_Paz")
            fecha_expiracion = fecha_expiracion_utc.astimezone(tz_la_paz)

            # Fecha actual en Bolivia
            fecha_actual = datetime.now(tz=tz_la_paz)
            return fecha_expiracion
        except Exception as e:
            return ResponseService._build_error_response(400, e)

    @staticmethod
    def validar_usuario(usuario_info):
        lockout_value = usuario_info.lockoutTime.value
        fecha_base_ad = datetime(1601, 1, 1, tzinfo=timezone.utc)

        # Si lockoutTime es mayor a la fecha base, el usuario está temporalmente bloqueado
        if lockout_value and lockout_value > fecha_base_ad:
            raise ValidationError("El usuario está bloqueado temporalmente en el Active Directory.")
        
        user_control = int(usuario_info.userAccountControl.value)
        if user_control & 2:
            raise ValidationError("La cuenta del usuario está deshabilitada en el Active Directory.")

        pwd_last_set = int(usuario_info.entry_raw_attributes['pwdLastSet'][0])
        
        if pwd_last_set == 0:
            raise ValidationError("El usuario debe cambiar su contraseña antes de poder iniciar sesión.")
        
    @staticmethod
    def validar_usuario_sin_cambio_contrasena(usuario_info):
        lockout_value = usuario_info.lockoutTime.value
        fecha_base_ad = datetime(1601, 1, 1, tzinfo=timezone.utc)

        # Si lockoutTime es mayor a la fecha base, el usuario está temporalmente bloqueado
        if lockout_value and lockout_value > fecha_base_ad:
            raise ValidationError("El usuario está bloqueado temporalmente en el Active Directory.")
        
        user_control = int(usuario_info.userAccountControl.value)
        if user_control & 2:
            raise ValidationError("La cuenta del usuario está deshabilitada en el Active Directory.")
            
    @staticmethod
    def obligarCambioContrasena(conn, dn):
        # Obligar al usuario a cambiar la contraseña al siguiente inicio de sesión
        conn.modify(dn, {'pwdLastSet': [(MODIFY_REPLACE, [0])]})
        
        if conn.result['result'] != 0:
            raise Exception(f"Error al obligar cambio de contraseña: {conn.result}")
        
    @staticmethod
    def habilitarCuenta(conn, dn):
            # Habilitar la cuenta del usuario
        conn.modify(dn, {'userAccountControl': [(MODIFY_REPLACE, [512])]})
        if conn.result['result'] != 0:
            raise ValidationError(f"Error al habilitar la cuenta.")

    @staticmethod
    def agregarNivelInternet(conn, dn, ldapBinddns):
        conn.search(ldapBinddns, '(cn=Internet_Nivel2)', attributes=['distinguishedName'])
        if conn.entries:
            group_dn = conn.entries[0].entry_dn
            conn.modify(group_dn, {'member': [(MODIFY_ADD, [dn])]})
            if conn.result['result'] != 0:
                raise ValidationError(f"Error al agregar el usuario al grupo.")
        else:
            raise ValidationError(f"No se encontró el grupo Internet_Nivel2.")
        
    @staticmethod
    def generarPassword():
        # Contraseña codificada en UTF-16
        contraseña = '"M1n.3c024*"'  # Contraseña entre comillas dobles
        password_utf16 = contraseña.encode('utf-16-le')
        return password_utf16
    
    @staticmethod
    def actualizar_expiracion_usuario(conn,usuario_info, usuario, fecha_expiracion, ou_usuario):

        # Convertir fecha a FILETIME
        filetime_value = LdapService.convertir_fecha_a_filetime(fecha_expiracion)
        usuario_dn = usuario_info.entry_dn
        conn.modify(
            usuario_dn,
            {"accountExpires": [(MODIFY_REPLACE, [filetime_value])]}
        )

        if conn.result["result"] != 0:
            raise ValidationError(f"No se pudo actualizar la expiración: {conn.result['message']}")

        info = (
            f"OU Actual: {ou_usuario}\n"
            f"Fecha Expiración: {fecha_expiracion}\n"
        )
        
        info_actual = usuario_info.info.value if usuario_info.info else ""
        nueva_info = f"{info_actual}\n{info}"

        conn.modify(
            usuario_dn,
            {"info": [(MODIFY_REPLACE, [nueva_info])]}
        )

        if conn.result["result"] != 0:
            raise ValidationError(f"No se pudo actualizar el campo info: {conn.result['message']}")
        

        return {
            "usuario": usuario,
            "ou": ou_usuario,
            "fecha_expiracion": fecha_expiracion,
            "info": nueva_info
        }

    @staticmethod
    def convertir_fecha_a_filetime(fecha_str):
        # Recibe formato: "2025-12-31"
        dt = datetime.strptime(fecha_str, "%Y-%m-%d")
        dt = dt.replace(tzinfo=timezone.utc)

        # FileTime: ticks desde 1601-01-01 en unidades de 100 nanosegundos
        FILETIME_EPOCH = datetime(1601, 1, 1, tzinfo=timezone.utc)
        filetime = int((dt - FILETIME_EPOCH).total_seconds() * 10**7)

        return str(filetime)

    @staticmethod
    def deshabilitar_usuario(conn, user_dn, user_account_control, usuario):
        # Verificar si ya está deshabilitado
        if user_account_control & 2 != 0:
            raise ValidationError(
                f"El usuario '{usuario}' ya está deshabilitado.",
                code="user_already_disabled"
            )

        new_uac = int(user_account_control) | 2

        conn.modify(
            user_dn,
            {"userAccountControl": [(MODIFY_REPLACE, [new_uac])]}
        )

        if conn.result["result"] != 0:
            raise ValidationError(f"No se pudo deshabilitar al usuario '{usuario}'.")

    @staticmethod
    def mover_usuario(conn, user_dn, display_name, nueva_ou):
        nuevo_dn = f"CN={display_name},{nueva_ou}"

        conn.modify_dn(
            user_dn,
            f"CN={display_name}",
            new_superior=nueva_ou
        )

        if conn.result["result"] != 0:
            raise ValidationError(
                f"Usuario deshabilitado, pero fallo moverlo a la OU destino: {conn.result['message']}"
            )

        return nuevo_dn

    @staticmethod
    def obtener_ou_usuario(conn, usuario):
            dn = conn.entries[0].entry_dn

            # Separar CN del resto del DN → OU + DC
            partes = dn.split(",", 1)
            if len(partes) < 2:
                raise ValidationError(f"No se pudo obtener la OU del usuario '{usuario}'.")

            ou = partes[1]  # Todo excepto el CN

            return ou
    
    @staticmethod
    def validar_ou_existe(conn: Connection, ou_dn: str):
        """
        Valida si una OU existe en Active Directory.

        Parámetros:
            conn: Conexión ldap3 válida y autenticada.
            ou_dn: DN de la OU que se desea verificar.
        
        Lanza:
            ValidationError si la OU no existe.
        """

        ou_existe = conn.search(
            search_base=ou_dn,
            search_filter="(objectClass=organizationalUnit)",
            attributes=["objectClass"]
        )

        if not ou_existe:
            raise ValidationError(f"La OU especificada no existe: {ou_dn}")

        return True
    
    @staticmethod
    def agregarUsuariosNivelInternet(conn, lista_usuarios, search_base, grupo_cn):
        # Buscar el grupo
        conn.search(
            search_base=search_base,
            search_filter=f"(cn={grupo_cn})",
            attributes=["distinguishedName"]
        )

        if not conn.entries:
            raise ValidationError(f"No se encontró el grupo {grupo_cn}.")

        group_dn = conn.entries[0].entry_dn

        # Procesar cada usuario de la lista
        for username in lista_usuarios:

            # Buscar DN del usuario por sAMAccountName
            conn.search(
                search_base=search_base,
                search_filter=f"(sAMAccountName={username})",
                attributes=["distinguishedName"]
            )

            if not conn.entries:
                raise ValidationError(f"No se encontró el usuario '{username}' en AD.")

            user_dn = conn.entries[0].entry_dn

            # Agregar al grupo
            conn.modify(group_dn, {"member": [(MODIFY_ADD, [user_dn])]})

            if conn.result["result"] != 0:
                raise ValidationError(
                    f"Error al agregar '{username}' al grupo {grupo_cn}: {conn.result}"
                )

