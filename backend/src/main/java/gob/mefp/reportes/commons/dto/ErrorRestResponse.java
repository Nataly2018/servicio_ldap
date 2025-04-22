package gob.mefp.reportes.commons.dto;

import gob.mefp.reportes.config.MensajesResponseRestGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorRestResponse {

    public static ResponseEntity<?> badRequest(Object listaError) {
        List<String> vListaErrores = new ArrayList<>();
        if (listaError instanceof String) {
            vListaErrores.add((String) listaError);
        } else {
            vListaErrores = (List<String>) listaError;
        }
        return getResponseGeneral(false, HttpStatus.BAD_REQUEST, MensajesResponseRestGeneral.TEXTO_RESPONSE_BAD , null, vListaErrores);
    }

    public static ResponseEntity<?> badRequestWithCode(Object listaError,Object data) {
        List<String> vListaErrores = new ArrayList<>();
        if (listaError instanceof String) {
            vListaErrores.add((String) listaError);
        } else {
            vListaErrores = (List<String>) listaError;
        }
        return getResponseGeneral(false, HttpStatus.BAD_REQUEST, MensajesResponseRestGeneral.TEXTO_RESPONSE_BAD , data, vListaErrores);
    }
    public static ResponseEntity<?> getResponseGeneral(Boolean success,
                                                       HttpStatus code, String message, Object data, Object errorList) {
        Map<String, Object> resultado=new HashMap<>();

        resultado.put("success",success);
        resultado.put("code", code.value());
        resultado.put("message",message);
        resultado.put("data",data);
        resultado.put("errorList",errorList);

        return new ResponseEntity<>(resultado,code);
    }

    public static ResponseEntity<?> noEncontrado(Object listaError) {
      /*  Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.FALSE);
        resultado.put("code",HttpStatus.NOT_FOUND.value());
        resultado.put("message", MensajesResponseRestGeneral.TEXTO_RESPUESTA_RECURSO_NO_ENCONTRODO);

        return new ResponseEntity<>(resultado,HttpStatus.NOT_FOUND);*/

        return getResponseGeneral(Boolean.FALSE,HttpStatus.NOT_FOUND, MensajesResponseRestGeneral.TEXTO_RESPUESTA_RECURSO_NO_ENCONTRODO, null, listaError);
    }


}
