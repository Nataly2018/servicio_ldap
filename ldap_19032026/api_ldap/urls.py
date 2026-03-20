from django.urls import  path
from .views.autenticacion_views import (
    DeshabilitarUsuarioApiView, EstadoUsuarioApiView, ObtenerInformacionUsuarioApiView,  AutenticacionUsuarioApiView, CrearUsuarioApiView, CambiarContrasenaAdminApiView, ObligarCambioContrasenaApiView, 
    ObtenerEstructuraApiView, CambiarContrasenaUsuarioApiView, HabilitarDeshabilitarUsuarioApiView, 
    ActualizarMoverUsuarioApiView, VerEstadoContrasenaApiView, VerificarSiExisteUsuarioApiView,VerificarSiExisteUsuarioCiApiView,
    ListarUsuariosActivosApiView 
)

urlpatterns = [
    path('obligar-cambio-contrasena/', ObligarCambioContrasenaApiView.as_view()),
    path('ver-estado-contrasena/', VerEstadoContrasenaApiView.as_view()),
    path('obtener-informacion-usuario/', ObtenerInformacionUsuarioApiView.as_view()),
    path('verificar-si-existe-usuario/', VerificarSiExisteUsuarioApiView.as_view()),
    path('verificar-si-existe-usuario-ci/', VerificarSiExisteUsuarioCiApiView.as_view()),
    path('autenticacion/', AutenticacionUsuarioApiView.as_view()),
    path('crear-usuario/', CrearUsuarioApiView.as_view()),
    path('cambiar-contrasena-admin/', CambiarContrasenaAdminApiView.as_view()),
    path('estructura/', ObtenerEstructuraApiView.as_view()),
    path('listar-usuarios-activos/', ListarUsuariosActivosApiView.as_view()),
    path('cambiar-contrasena-usuario/', CambiarContrasenaUsuarioApiView.as_view()),
    path('deshabilitar-usuario/', DeshabilitarUsuarioApiView.as_view()),
    path('habilitar-deshabilitar/', HabilitarDeshabilitarUsuarioApiView.as_view()),
    path('estado-usuario/', EstadoUsuarioApiView.as_view()),
    path('modificar-usuario/', ActualizarMoverUsuarioApiView.as_view()),
]