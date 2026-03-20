from rest_framework import serializers

def validar_no_vacio(value, field_name):
    if not value or (isinstance(value, str) and value.strip() == ""):
        raise serializers.ValidationError(f"El campo no puede estar vacío")
    return value

def validar_longitud(value, field_name, min_len=1, max_len=50):
    if not (min_len <= len(value) <= max_len):
        raise serializers.ValidationError(
            f"El campo debe tener entre {min_len} y {max_len} caracteres"
        )
    return value

def validar_sin_espacios(value, field_name):
    if " " in value:
        raise serializers.ValidationError(f"El campo no debe contener espacios en blanco")
    return value

def validar_numerico(value, field_name):
    if not value.isdigit():
        raise serializers.ValidationError(f"El campo debe contener solo números")
    return value
