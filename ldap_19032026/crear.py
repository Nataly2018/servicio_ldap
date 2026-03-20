import winrm
import chardet
import argparse

def crear_user():
    print("Holaaaa desde crearArchivo")

# Configurar el parser de argumentos
parser = argparse.ArgumentParser(description="Agregar un nuevo usuario")
parser.add_argument("nombre_usuario", help="Nombre de usuario")
parser.add_argument("nombre", help="Nombre del usuario")
parser.add_argument("apellido", help="Apellido del usuario")
parser.add_argument("cedula_identidad", help="Cédula de identidad del usuario")
parser.add_argument("cargo", help="Cargo del usuario")
parser.add_argument("departamento", help="Departamento del usuario")
parser.add_argument("tipo_personal", choices=["planta", "temporal"], help="Tipo de personal (planta o temporal)")
parser.add_argument("fecha_expiracion", nargs="?", help="Fecha de expiración (solo para temporal)")

args = parser.parse_args()

# Configura los datos del servidor y las credenciales
session = winrm.Session(
    'https://172.20.251.40:5986/wsman', 
    auth=('loginUTI@evirtual.economiayfinanzas.gob.bo', 'T3mp0r4l2021'), 
    transport='ssl', 
    server_cert_validation='ignore'  # Ignora la validación del certificado si es autofirmado
)

# Ruta del script en el servidor
script_path = r'C:\Users\loginUTI\Documents\crear_usuario.ps1'

# Comando para ejecutar el script con parámetros
command = f'PowerShell -ExecutionPolicy Bypass -File "{script_path}" -NombreUsuario "{args.nombre_usuario}" -Nombre "{args.nombre}" -Apellido "{args.apellido}" -CedulaIdentidad "{args.cedula_identidad}" -Cargo "{args.cargo}" -Departamento "{args.departamento}" -TipoPersonal "{args.tipo_personal}"'

# Si es temporal, también pasamos la fecha de expiración
if args.tipo_personal == "temporal" and args.fecha_expiracion:
    command += f' -FechaExpiracion "{args.fecha_expiracion}"'

# Ejecuta el comando remoto
result = session.run_cmd(command)

# Detecta la codificación
encoding = chardet.detect(result.std_out)['encoding']

# Si encoding es None, usamos 'utf-8' como fallback
if not encoding:
    encoding = 'utf-8'

# Decodifica la salida estándar
stdout = result.std_out.decode(encoding) if result.std_out else "No STDOUT"
stderr = result.std_err.decode(encoding) if result.std_err else "No STDERR"

# Muestra los resultados
print("STDOUT:", stdout)
print("STDERR:", stderr)
