from django.conf import settings
from ldap3 import Server, Connection, Tls, ALL, SUBTREE , MODIFY_ADD, MODIFY_REPLACE # Importar SUBTREE correctamente
import ssl # Importar ssl para la configuración de TLS
from django.http import JsonResponse
from rest_framework.response import Response
from django.shortcuts import get_object_or_404
from django.core.exceptions import ImproperlyConfigured

from utils.responses import ResponseService

class ConexionLdap: 
    def conexion_ldap():
        try: 
            ipLdapServer = getattr(settings, "IP_LDAP_SERVER", "Default Value")
            ldapPassword = getattr(settings, "LDAP_PASSWORD", "Default Value")
            ldapUser = getattr(settings, "LDAP_USER", "Default Value")
            ldapBinddns = getattr(settings, "LDAP_BINDDNS", "Default Value")
            ldapTimeOut = getattr(settings, "LDAP_TIMEOUT", "Default Value")
            ldapReceivedTimeOut = getattr(settings, "LDAP_RECEIVED_TIMEOUT", "Default Value")

            # server = Server(ipLdapServer, get_info=ALL)
            # conn = Connection(server, user=ldapUser, password=ldapPassword, auto_bind=True)

            server = Server(ipLdapServer, use_ssl=True, get_info=ALL, tls=Tls(validate=ssl.CERT_NONE),connect_timeout=ldapTimeOut)
            conn = Connection(server, user=ldapUser, password=ldapPassword, auto_bind=True, receive_timeout=ldapReceivedTimeOut )

            if not conn.bound:
                raise ImproperlyConfigured("No se pudo enlazar al servidor LDAP")
            
            if conn.bind():
                # Llamada a la función con el nombre de usuario proporcionado desde la línea de comandos
                return conn
                
        except Exception as e:
            return ResponseService._build_error_response(500, e)

            # Código que maneja la excepción
            # Este bloque solo se ejecuta si ocurre la excepción especificada


    def desvincular_conexion_ldap(conn):
        conn.unbind();