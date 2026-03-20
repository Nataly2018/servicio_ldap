from django.http import JsonResponse
from utils.generic_response_serealizer import GenericResponseSerializer

class ResponseService:

    @staticmethod
    def _build_error_response(code, exception=None, message=None,data=None):
        """Método auxiliar para respuestas de error uniformes"""
        response_data = {
            "data": data or {},
            "statusCode": code,
            "message": message or (
                " ".join(exception.messages) if hasattr(exception, "messages") else str(exception)
            ),
            "success": False,
        }
        serializer = GenericResponseSerializer(response_data)
        return JsonResponse(serializer.data, status=code)
    @staticmethod
    def _build_success_response(code=200, message="Operación exitosa", data=None):
        """Método auxiliar para respuestas exitosas uniformes"""
        response_data = {
            "data": data or {},
            "statusCode": code,
            "message": message,
            "success": True,
        }
        serializer = GenericResponseSerializer(response_data)
        return JsonResponse(serializer.data, status=code)