from rest_framework import serializers
from utils.validators import (
    validar_no_vacio,
    validar_longitud,
    validar_sin_espacios,
)

class UsuarioUpdateRequestSerializer(serializers.Serializer):
    usuario = serializers.CharField(required=True, allow_blank=False)
    nuevoNombreUsuario = serializers.CharField(required=False, allow_blank=False)
    nombre = serializers.CharField(required=True, allow_blank=False)
    apellido = serializers.CharField(required=True, allow_blank=False)
    cargo = serializers.CharField(required=True, allow_blank=False)
    departamento = serializers.CharField(required=True, allow_blank=False)
    cedulaIdentidad = serializers.CharField(required=True, allow_blank=False)
    fechaExpiracion = serializers.CharField(required=False)
    interno = serializers.CharField(required=False)
    piso = serializers.CharField(required=False)

    def validate_usuario(self, value):
        value = validar_longitud(value, "usuario", 1, 20)
        value = validar_sin_espacios(value, "usuario")
        return value
    
    def validate_nuevoNombreUsuario(self, value):
        value = validar_longitud(value, "nuevoNombreUsuario", 1, 20)
        value = validar_sin_espacios(value, "nuevoNombreUsuario")
        return value

    def validate_cedulaIdentidad(self, value):
        value = validar_no_vacio(value, "cedulaIdentidad")
        value = validar_sin_espacios(value, "cedulaIdentidad")
        return value

    