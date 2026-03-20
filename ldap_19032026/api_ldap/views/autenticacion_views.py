from api_ldap.decorators.decorators import validate_field, validate_length, no_internal_spaces, validate_with
from api_ldap.serealizers.autenticacion_request_serealizer import AutenticacionRequestSerializer
from api_ldap.serealizers.deshabilitar_request_serealizer import DeshabilitarRequestSerializer
from api_ldap.serealizers.estado_usuario_request_serealizer import EstadoUsuarioRequestSerializer
from api_ldap.serealizers.habilitar_deshabilitar_request_serealizer import HabilitarDeshabilitarRequestSerializer
from api_ldap.serealizers.usuario_create_request_serealizer import UsuarioCreateRequestSerializer
from api_ldap.serealizers.admin_update_password_usuario_request_serealizer import AdminUpdatePasswordUsuarioRequestSerializer
from api_ldap.serealizers.usuario_update_password_request_serealizer import UsuarioUpdatePasswordRequestSerializer
from api_ldap.serealizers.usuario_update_request_serealizer import UsuarioUpdateRequestSerializer
from api_ldap.serealizers.verificar_existe_usuario_ci_request_serealizer import VerificarExisteUsuarioCiRequestSerializer
from api_ldap.services.deshabilitar_usuario_service import deshabilitar_y_mover_usuario_service
from api_ldap.services.estado_usuario_service import estado_usuario_service
from api_ldap.services.obligar_cambio_contrasena_service import obligar_cambio_contrasena
from api_ldap.services.crear_usuario_service import crear_usuario_service
from api_ldap.services.ver_estado_contrasena_service import ver_estado_contrasena
# from api_ldap.serealizers.admin_update_password_usuario_request_serealizer import AdminUpdatePasswordUsuarioRequestSerializer
from utils.generic_response_serealizer import GenericResponseSerializer
from rest_framework.views import APIView
from rest_framework import status
from django.http import JsonResponse
from rest_framework.response import Response
from django.shortcuts import get_object_or_404
from datetime import datetime
from django.conf import settings
from django.core.exceptions import ValidationError
from django.core.exceptions import ImproperlyConfigured

from utils.serealizer_helpers import join_serializer_errors
from ..services.listar_usuario_activos_service import ListarUsuariosActivosService
from ..services.cambio_contrasena_admin_service import cambio_contrasena_admin_service
from ..services.ver_arbol_service import ver_arbol_service
from ..services.obtener_informacion_usuario_service import obtener_informacion_usuario_service
from ..services.autenticacion_service import autenticacion_service
from ..services.cambiar_contrasena_usuario_service import cambiar_contrasena_usuario_service
from ..services.habilitar_deshabilitar_usuario_service import habilitar_o_deshabilitar_usuario_service
from ..services.actualizar_usuario_service import actualizar_usuario_service
from ..services.existe_usuario_service import existe_usuario_service
from ..services.verificar_si_existe_usuario_ci_service import verificar_existe_usuario_ci_service
from ..Forms.Existeusuario import ExisteUsuarioForm
from utils.responses import ResponseService
import re

from drf_yasg.utils import swagger_auto_schema
from drf_yasg import openapi

#from drf_yasg import openapi

class ObligarCambioContrasenaApiView(APIView):
    @swagger_auto_schema(
        operation_description="Verifica si la contraseña del usuario debe ser actualizada al siguiete intento de login",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "nombreUsuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["nombreUsuario"],
            example={"nombreUsuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    @validate_field({"nombreUsuario": str})
    @validate_length({"nombreUsuario": (1, 50)})
    @no_internal_spaces(["nombreUsuario"])
    def post(self, request):
        try:
            nombre_usuario = request.validated_data["nombreUsuario"]
            # Lógica de negocio
            return obligar_cambio_contrasena(nombre_usuario)
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class VerEstadoContrasenaApiView(APIView):
    @swagger_auto_schema(
        operation_description="Verifica si la contraseña del usuario debe ser actualizada al siguiete intento de login",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "usuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["usuario"],
            example={"usuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    @validate_field({"usuario": str})
    @validate_length({"usuario": (1, 50)})
    @no_internal_spaces(["usuario"])
    def post(self, request):
        try:
            usuario = request.validated_data["usuario"]
            # Lógica de negocio
            return ver_estado_contrasena(usuario)
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class VerificarSiExisteUsuarioCiApiView(APIView):
    @swagger_auto_schema(
        operation_description="Verifica si un usuario y ci existe en el directorio LDAP",
        request_body= VerificarExisteUsuarioCiRequestSerializer,
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        try:
            serializer = VerificarExisteUsuarioCiRequestSerializer(data=request.data)
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )

            data = serializer.validated_data
            # Lógica de negocio
            return verificar_existe_usuario_ci_service(data)
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)
        except Exception as e:
            return ResponseService._build_error_response(500, e)

class VerificarSiExisteUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Verifica si un usuario existe en el directorio LDAP",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "nombreUsuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["nombreUsuario"],
            example={"nombreUsuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    @validate_field({"usuario": str})
    @validate_length({"usuario": (1, 50)})
    @no_internal_spaces(["usuario"])
    def post(self, request):
        try:
            nombre_usuario = request.validated_data["usuario"]
            # Lógica de negocio
            return existe_usuario_service(nombre_usuario)
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)
        except Exception as e:
            return ResponseService._build_error_response(500, e)

class ObtenerInformacionUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Verifica si un usuario existe en el directorio LDAP",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "usuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["usuario"],
            example={"usuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    @validate_field({"usuario": str})
    @validate_length({"usuario": (1, 50)})
    @no_internal_spaces(["usuario"])
    def post(self, request):
        try:
            nombre_usuario = request.validated_data["usuario"]
            # Lógica de negocio
            return obtener_informacion_usuario_service(nombre_usuario)
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)
        except Exception as e:
            return ResponseService._build_error_response(500, e)

class CrearUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Crea un nuevo usuario en el directorio LDAP",
        request_body=UsuarioCreateRequestSerializer,
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )

    def post(self, request):
        try:
            # Definir los campos requeridos
            serializer = UsuarioCreateRequestSerializer(data=request.data)

            # Validar entrada
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )

            data = serializer.validated_data

            # Llamada a la función que crea el usuario
            return crear_usuario_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class AutenticacionUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Autentica un usuario contra LDAP",
        request_body=AutenticacionRequestSerializer,
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        try:
            serializer = AutenticacionRequestSerializer(data=request.data)
            
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )
            
            data = serializer.validated_data

            return autenticacion_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class CambiarContrasenaAdminApiView(APIView):
    @swagger_auto_schema(
        operation_description="Cambia la contraseña de un usuario como admin en LDAP",
        request_body=AdminUpdatePasswordUsuarioRequestSerializer,
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )

    def post(self, request):
        try:
            #Definir los campos requeridos
            serializer = AdminUpdatePasswordUsuarioRequestSerializer(data=request.data)

            # Validar entrada
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )

            data = serializer.validated_data

            # Llamada a la función que crea el usuario
            return cambio_contrasena_admin_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class ObtenerEstructuraApiView(APIView):
    @swagger_auto_schema(
        operation_description="Obtiene la estructura jerárquica del LDAP",
        responses={
            200: openapi.Response(
                description="Estructura LDAP obtenida correctamente",
                example={
                    "application/json": {
                        "statusCode": 201,
                        "success": True,
                        "message": "Árbol obtenido exitosamente",
                        "data": [
                            {
                                "nombre": "Unidad 1",
                                "subunidades": [
                                    {"nombre": "Sección A"},
                                    {"nombre": "Sección B"}
                                ]
                            },
                            {
                                "nombre": "Unidad 2",
                                "subunidades": []
                            }
                        ]
                    }
                }
            ),
            500: openapi.Response(
                description="Error interno del servidor",
                example={
                    "application/json": {
                        "statusCode": 500,
                        "success": False,
                        "message": "Error al obtener los datos"
                    }
                }
            )
        }
    )
    def get(self, request):
        try:
            # Llamamos a la función ver_arbol() para obtener la jerarquía LDAP
            result = ver_arbol_service()
            
            if 'error' in result:
                return JsonResponse({
                    "statusCode": 500,
                    "success": False,
                    "message": 'Error al obtener los datos',
                }, 
                status=500
                )       
            return JsonResponse({
                "statusCode": 201,
                "success": True,
                "message": 'Árbol obtenido exitosamente',
                "data": result
                }, 
                status=201
            )
        
        except Exception as e:
            return JsonResponse(
                {'message': f'Error inesperado: {str(e)}'}, 
                status=status.HTTP_500_INTERNAL_SERVER_ERROR
            )  

class CambiarContrasenaUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Cambia la contraseña de un usuario con rol de usuario en LDAP",
        request_body=UsuarioUpdatePasswordRequestSerializer,
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        try:
            #Definir los campos requeridos
            serializer = UsuarioUpdatePasswordRequestSerializer(data=request.data)
            
            # Validar entrada
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )

            data = serializer.validated_data

            # Llamada a la función que crea el usuario
            return cambiar_contrasena_usuario_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class DeshabilitarUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Habilita o deshabilita un usuario en LDAP",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "nombreUsuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["nombreUsuario"],
            example={"nombreUsuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        try:
            serializer = DeshabilitarRequestSerializer(data=request.data)
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )
            
            data = serializer.validated_data

            # Llamar a la función que maneja la habilitación/deshabilitación
            return deshabilitar_y_mover_usuario_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class HabilitarDeshabilitarUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Habilita o deshabilita un usuario en LDAP",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "nombreUsuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["nombreUsuario"],
            example={"nombreUsuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        try:
            serializer = HabilitarDeshabilitarRequestSerializer(data=request.data)
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )
            
            data = serializer.validated_data

            # Llamar a la función que maneja la habilitación/deshabilitación
            return habilitar_o_deshabilitar_usuario_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class EstadoUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Obtiene el estado del usuario en LDAP",
        request_body=openapi.Schema(
            type=openapi.TYPE_OBJECT,
            properties={
                "usuario": openapi.Schema(type=openapi.TYPE_STRING, description="Nombre de usuario (sAMAccountName)")
            },
            required=["usuario"],
            example={"usuario": "juan.perez"}
        ),
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        try:
            serializer = EstadoUsuarioRequestSerializer(data=request.data)
            if not serializer.is_valid():
                return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )
            
            data = serializer.validated_data

            # Llamar a la función que maneja la habilitación/deshabilitación
            return estado_usuario_service(data)

        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class ActualizarMoverUsuarioApiView(APIView):
    @swagger_auto_schema(
        operation_description="Actualiza datos del usuario y lo mueve a otra ubicación en LDAP",
        request_body=UsuarioUpdateRequestSerializer,
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def post(self, request):
        # Se requiere al menos el campo 'usuario'
        serializer = UsuarioUpdateRequestSerializer(data=request.data)
        if not serializer.is_valid():
            return ResponseService._build_error_response(
                    code=400,
                    message=join_serializer_errors(serializer)
                )
        data = serializer.validated_data

        try:
            return actualizar_usuario_service(data)
        
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)

class ListarUsuariosActivosApiView(APIView):
    @swagger_auto_schema(
        operation_description="Lista los usuarios activos en el directorio LDAP",
        responses={
            200: openapi.Response(
                description="Respuesta exitosa",
            ),
            400: openapi.Response(
                description="Error de validación",
            ),
            500: openapi.Response(
                description="Error interno",
            ),
        }
    )
    def get(self, request):
        service = ListarUsuariosActivosService()
        try:
            return service.listar()
        
        except ValidationError as e:
            return ResponseService._build_error_response(400, e)
        except ImproperlyConfigured as e:
            return ResponseService._build_error_response(500, e)