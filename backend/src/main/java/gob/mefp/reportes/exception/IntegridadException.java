package gob.mefp.reportes.exception;
import lombok.extern.slf4j.Slf4j;

import static gob.mefp.reportes.config.MensajesExcepciones.TEXTO_EXCEPCION_INTEGRIDAD;

@Slf4j
public class IntegridadException extends  RuntimeException{
    public IntegridadException(String message, Exception exp) {
        super(TEXTO_EXCEPCION_INTEGRIDAD+message);

    }
    public IntegridadException(String message) {
        super(TEXTO_EXCEPCION_INTEGRIDAD+message);
    }
}
