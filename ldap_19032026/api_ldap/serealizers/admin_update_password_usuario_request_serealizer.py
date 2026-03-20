from rest_framework import serializers
from utils.validators import (
    validar_no_vacio,
    validar_longitud,
    validar_sin_espacios,
)

class AdminUpdatePasswordUsuarioRequestSerializer(serializers.Serializer):
    usuario = serializers.CharField(required=True, allow_blank=False, initial="prueba.ldap")
    nuevaContrasena = serializers.CharField(required=True, allow_blank=False)
    esAdmin = serializers.BooleanField(required=True,  initial=False)

    def validate_usuario(self, value):
        value = validar_longitud(value, "usuario", 1, 20)
        value = validar_sin_espacios(value, "usuario")
        return value
    
    """ def validate_nuevaContrasena(self, value):
        value = validar_longitud(value, "usuario", 1, 20)
        return value """