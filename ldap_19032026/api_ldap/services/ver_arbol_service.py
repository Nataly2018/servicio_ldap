from django.conf import settings
from .conexion_ldap_service import ConexionLdap
from django.core.exceptions import ValidationError
import logging
import json
from ldap3 import  SUBTREE

def ver_arbol_service():
    try:
        conn = ConexionLdap.conexion_ldap()
        ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")

        def obtener_ou_hierarchy(conn, base_dn, processed_ous=None):
            if processed_ous is None:
                processed_ous = set()  # Usamos un conjunto para registrar OUs ya procesadas
            
            # Realizamos la búsqueda de OUs bajo el contenedor base_dn
            conn.search(base_dn, '(objectClass=organizationalUnit)', SUBTREE, attributes=['distinguishedName', 'ou'])
            
            ou_list = []  # Lista para almacenar las OUs en formato de objeto
            
            for entry in conn.entries:
                ou_dn = entry.distinguishedName.value
                if ou_dn in processed_ous:
                    continue  # Evitar ciclos
                
                processed_ous.add(ou_dn)  # Marcamos esta OU como procesada
                
                # Obtener subgrupos (subOUs)
                subgrupos = obtener_ou_hierarchy(conn, ou_dn, processed_ous)  # Llamada recursiva
                
                # Si no hay subgrupos, asignamos None en vez de lista vacía
                if not subgrupos:
                    subgrupos = None  # O puedes usar `subgrupos = []` si prefieres un array vacío en lugar de null

                ou_object = {
                    'grupo': entry.ou.value,
                    'distinguishedName': ou_dn,
                    'subGrupo': subgrupos  # Asignamos el valor de subgrupos
                }
                
                ou_list.append(ou_object)
            
            return ou_list
        
        # Obtener la jerarquía de OUs
        ou_hierarchy = obtener_ou_hierarchy(conn, ldapBinddns)
        
        # Convertir la jerarquía de OUs a JSON
        json_output = json.dumps(ou_hierarchy, indent=4)
        
        return ou_hierarchy  # Retornar el resultado en lugar de imprimirlo
    
    except Exception as e:
        logging.error(f"Error al obtener la jerarquía de OUs: {e}")
        return {'error': str(e)}  # En caso de error, devolver un mensaje adecuado
    
    finally:
        if conn:
            conn.unbind()  # Cerrar la conexión correctamente
            ConexionLdap.desvincular_conexion_ldap(conn)  # Llamada a desvincular la conexión