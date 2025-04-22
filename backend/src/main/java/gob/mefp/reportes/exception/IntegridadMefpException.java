/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.exception;
import lombok.extern.slf4j.Slf4j;

import static gob.mefp.reportes.config.MensajesExcepciones.TEXTO_EXCEPCION_INTEGRIDAD;

@Slf4j
public class IntegridadMefpException extends  RuntimeException{
    public IntegridadMefpException(String message, Exception exp) {
        super(TEXTO_EXCEPCION_INTEGRIDAD+message);

    }
    public IntegridadMefpException(String message) {
        super(TEXTO_EXCEPCION_INTEGRIDAD+message);
    }
}
