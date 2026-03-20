from functools import wraps
import json
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework import status

from utils.responses import ResponseService

def validate_length(length_rules):
    """
    Decorador para validar la longitud mínima y máxima de campos string.
    
    length_rules: dict {campo: (min_length, max_length)}
    """
    def decorator(func):
        @wraps(func)
        def wrapper(self, request, *args, **kwargs):
            data = getattr(request, "validated_data", None)
            if not data:
                # Si no hay datos validados, devolver error
                return ResponseService._build_error_response(400, message="No hay datos validados para verificar longitud")

            for field, (min_len, max_len) in length_rules.items():
                value = data.get(field)
                if value is None:
                    continue  # La existencia ya fue validada en validate_json
                if not isinstance(value, str):
                    continue  # Solo aplicable a strings
                if len(value) < min_len or len(value) > max_len:
                    return ResponseService._build_error_response(
                        400,
                        message=f"'{field}' debe tener entre {min_len} y {max_len} caracteres"
                    )
            return func(self, request, *args, **kwargs)
        return wrapper
    return decorator
def validate_field(required_fields):
    def decorator(func):
        @wraps(func)
        def wrapper(self, request, *args, **kwargs):
            #if request.method != "POST":
            #    return _build_error_response(405, message="Método no permitido")

            try:
                data = getattr(request, "validated_data", None) or request.data
            except Exception as e:
                return ResponseService._build_error_response(400, exception=e, message="JSON inválido")

            for field, field_type in required_fields.items():
                value = data.get(field)

                if value is None:
                    return ResponseService._build_error_response(400, message=f"'{field}' es obligatorio")

                if not isinstance(value, field_type):
                    return ResponseService._build_error_response(400, message=f"'{field}' debe ser de tipo {field_type.__name__}")

                if isinstance(value, str) and value.strip() == "":
                    return ResponseService._build_error_response(400, message=f"'{field}' no puede estar vacío")

            request.validated_data = data
            return func(self, request, *args, **kwargs)

        return wrapper
    return decorator
def validate_type(required_fields):
    """
    Decorador para DRF Class-Based Views o funciones
    required_fields: dict {campo: tipo}
    """
    def decorator(func):
        @wraps(func)
        def wrapper(self, request, *args, **kwargs):
            
            try:
                data = request.data  # DRF ya parsea JSON automáticamente
            except Exception as e:
                return ResponseService._build_error_response(400, exception=e, message="JSON inválido")

            # Validar campos
            for field, field_type in required_fields.items():
                value = data.get(field)
                if value is None:
                    return ResponseService._build_error_response(400, message=f"'{field}' es obligatorio")
                if field_type == str and value.strip() == "":
                    return ResponseService._build_error_response(400, message=f"'{field}' no puede estar vacío")
                if not isinstance(value, field_type):
                    return ResponseService._build_error_response(400, message=f"'{field}' debe ser de tipo {field_type.__name__}")

            # Pasar data validada al método
            request.validated_data = data
            return func(self, request, *args, **kwargs)
        return wrapper
    return decorator
def no_internal_spaces(fields):
    """
    Decorador para validar que los campos de tipo string
    no tengan espacios en blanco internos.
    
    fields: lista de nombres de campos a validar
    """
    def decorator(func):
        @wraps(func)
        def wrapper(self, request, *args, **kwargs):
            data = getattr(request, "validated_data", None)
            if not data:
                return ResponseService._build_error_response(400, message="No hay datos validados para verificar espacios internos")

            for field in fields:
                value = data.get(field)
                if value is None:
                    continue  # La existencia ya fue validada en validate_json
                if not isinstance(value, str):
                    continue
                if " " in value:
                    return ResponseService._build_error_response(
                        400,
                        message=f"'{field}' no debe contener espacios en blanco"
                    )
            return func(self, request, *args, **kwargs)
        return wrapper
    return decorator
def validate_with(serializer_class):
    """
    Decorador que valida la entrada con un serializer
    y deja los datos limpios en request.dto
    """
    def decorator(func):
        @wraps(func)
        def wrapper(self, request, *args, **kwargs):
            serializer = serializer_class(data=request.data)
            if not serializer.is_valid():
                return Response(
                    {"message": "Error de validación", "errors": serializer.errors},
                    status=status.HTTP_400_BAD_REQUEST,
                )
            # Guardar dto validado
            request.dto = serializer.validated_data
            return func(self, request, *args, **kwargs)
        return wrapper
    return decorator