from ldap3 import SUBTREE
from django.conf import settings
from utils.responses import ResponseService
from .conexion_ldap_service import ConexionLdap
from .ldap_service import LdapService


class ListarUsuariosActivosService:

    def listar(self):
        conn = None

        try:
            conn = ConexionLdap.conexion_ldap()

            ldapBinddns = getattr(settings, "LDAP_BINDDNS", "")
            dominio_dn = ",".join([x for x in ldapBinddns.split(",") if x.strip().startswith("DC=")])
            ldapPass = getattr(settings, "LDAP_PASSWORD", "")

            # Filtro: usuarios activos (no deshabilitados) y que tengan ipPhone
            filtro = "(&(objectCategory=person)(objectClass=user)(!(userAccountControl:1.2.840.113556.1.4.803:=2))(ipPhone=*))"

            conn.search(
                search_base=dominio_dn,
                search_filter=filtro,
                search_scope=SUBTREE,
                attributes=["cn", "ipPhone", "sAMAccountName", "physicalDeliveryOfficeName"]
            )

            usuarios = []
            lista_usuarios = conn.entries
            for entry in conn.entries:
                #usu
                nombre = entry.cn.value if hasattr(entry, "cn") else None
                interno = entry.ipPhone.value if hasattr(entry, "ipPhone") else None
                usuario = entry.sAMAccountName.value if hasattr(entry, "sAMAccountName") else None
                ubicacion = entry.physicalDeliveryOfficeName.value if hasattr(entry, "physicalDeliveryOfficeName") else None

                usuarios.append({
                    "nombre": nombre,
                    "interno": interno,
                    "usuario": usuario,
                    "piso": ubicacion
                })

            # Ordenamos por nombre
            usuarios = sorted(usuarios, key=lambda x: x["nombre"].lower())

            return ResponseService._build_success_response(
                message="Usuarios activos obtenidos correctamente",
                data={"usuarios": usuarios}
            )

        except Exception as e:
            return ResponseService._build_error_response(
                code=500,
                message=f"Error al consultar usuarios: {str(e)}"
            )

        finally:
            if conn:
                ConexionLdap.desvincular_conexion_ldap(conn)
