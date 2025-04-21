/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "IntegridadYpfbException.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 5/22/2023 1:48 p. m.
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.exception;
import lombok.extern.slf4j.Slf4j;

import static gob.ypfb.nova.config.MensajesExcepciones.TEXTO_EXCEPCION_INTEGRIDAD;

@Slf4j
public class IntegridadYpfbException extends  RuntimeException{
    public IntegridadYpfbException(String message,Exception exp) {
        super(TEXTO_EXCEPCION_INTEGRIDAD+message);
        log.error("Error de Integridad",exp);

    }
    public IntegridadYpfbException(String message) {
        super(TEXTO_EXCEPCION_INTEGRIDAD+message);
        log.error("Error de Integridad"+message);
    }
}
