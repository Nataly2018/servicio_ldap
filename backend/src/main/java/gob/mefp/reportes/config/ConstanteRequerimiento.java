/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.config;

public class ConstanteRequerimiento {




    public enum EnumEstadoFormularioRequerimiento {
        REGISTRO,
        ENVIADO;
        EnumEstadoFormularioRequerimiento() {

        }
    }

    public enum EnumEstadoRequerimiento {
        REGISTRO,
        ENVIADO,
        CONSULTA,
        REVISION,
        ACEPTADO,
        RECHAZADO;
        EnumEstadoRequerimiento() {

        }
    }

    public enum EnumEstadoParticipante {
        ASIGNADO,
        NO_ASIGNADO;
    }
    public enum EnumTipoParticipante {
        RESPONSABLE,
        PARTICIPANTE;
    }
    public enum EnumEstadoComentario {
        COMENTARIO,
        COMENTARIO_INTERNO;
    }

    public enum EnumAccionNotificacion{
        INICIO,
        ASIGNACION,
        PARTICIPANTE,
        CONSULTAR,
        ACEPTAR,
        RECHAZAR,
        RESPONDER,
        COMENTARIO;
    }
}
