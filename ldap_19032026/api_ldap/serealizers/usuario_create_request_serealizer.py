from rest_framework import serializers
from utils.validators import (
    validar_no_vacio,
    validar_longitud,
    validar_sin_espacios,
)

class UsuarioCreateRequestSerializer(serializers.Serializer):
    ubicacion = serializers.CharField(required=True, allow_blank=False, initial="OU=pasantes, OU=UTI, OU=DGAA")
    usuario = serializers.CharField(required=True, allow_blank=False, initial="jperez")
    nombre = serializers.CharField(required=True, allow_blank=False, initial="Juan")
    apellido = serializers.CharField(required=True, allow_blank=False, initial="Perez")
    cedulaIdentidad = serializers.CharField(required=True, allow_blank=False, initial="12345678")
    cargo = serializers.CharField(required=True, allow_blank=False, initial="Analista")
    departamento = serializers.CharField(required=True, allow_blank=False, initial="TI")
    fechaExpiracion = serializers.CharField(required=False, initial="2025-10-10")
    interno = serializers.CharField(required=False, initial="0000")
    piso = serializers.CharField(required=False, initial="12")

    # 🔹 Validaciones personalizadas campo por campo
    def validate_usuario(self, value):
        value = validar_longitud(value, "usuario", 1, 20)
        value = validar_sin_espacios(value, "usuario")
        return value

    def validate_cedulaIdentidad(self, value):
        value = validar_no_vacio(value, "cedulaIdentidad")
        value = validar_sin_espacios(value, "cedulaIdentidad")
        return value
