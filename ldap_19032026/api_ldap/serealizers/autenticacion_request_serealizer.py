from rest_framework import serializers
from utils.validators import (
    validar_no_vacio,
    validar_longitud,
    validar_sin_espacios,
)

class AutenticacionRequestSerializer(serializers.Serializer):
    usuario = serializers.CharField(required=True, allow_blank=False, initial="prueba.ldap")
    contrasena = serializers.CharField(required=True, allow_blank=False)

    def validate_usuario(self, value):
        value = validar_longitud(value, "usuario", 1, 20)
        value = validar_sin_espacios(value, "usuario")
        return value