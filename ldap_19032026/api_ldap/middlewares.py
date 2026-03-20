# middlewares.py
from django.urls import resolve, Resolver404
from django.http import HttpResponseNotFound

from utils.responses import ResponseService

class ValidarRutaMiddleware:
    """
    Middleware que valida si la ruta ingresada existe en urls.py
    antes de ejecutar cualquier vista.
    """
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        try:
            # Verifica si la ruta existe
            resolve(request.path)
        except Resolver404:
            # Si no existe, devuelve un 404 y no pasa al servicio
            return ResponseService._build_error_response(404, f"La ruta '{request.path}' no existe en este servicio")

        # Si la ruta existe, continúa normalmente
        response = self.get_response(request)
        return response
    
