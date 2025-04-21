package gob.mefp.reportes.exception;

import gob.ypfb.nova.config.MensajesResponseRestGeneral;

public class InvalidException extends RuntimeException{

    public InvalidException(String mensaje){
        super(MensajesResponseRestGeneral.TEXTO_INVALID_EXCEPTION+mensaje);
    }
}
