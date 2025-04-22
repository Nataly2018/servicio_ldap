package gob.mefp.reportes.commons.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gob.mefp.reportes.config.MensajesResponseRestGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


public class ResponseRest {

    public static ResponseEntity<?> ok(Object data){
        return getResponseGeneral(Boolean.TRUE,HttpStatus.OK,MensajesResponseRestGeneral.TEXTO_RESPONSE_OK,data);
    }

    public static ResponseEntity<?> creado(Object data) {

        return getResponseGeneral(Boolean.TRUE,HttpStatus.CREATED,MensajesResponseRestGeneral.TEXTO_RESPONSE_OK,data);

    }

    public static ResponseEntity<?> actualizado(Object data) {
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.TRUE);
        resultado.put("code",HttpStatus.OK.value());
        resultado.put("message", MensajesResponseRestGeneral.TEXTO_RESPONSE_OK);
        resultado.put("data",data);
        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }

    //
    public static ResponseEntity<?> sinContenido() {
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.TRUE);
        resultado.put("code",HttpStatus.NO_CONTENT.value());
        resultado.put("message", MensajesResponseRestGeneral.TEXTO_RESPUESTA_SIN_CONTENIDO);

        return new ResponseEntity<>(resultado,HttpStatus.OK);
    }


    //403
    public static ResponseEntity<?> prohibido(String mensaje) {
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.FALSE);
        resultado.put("code",HttpStatus.FORBIDDEN.value());
        resultado.put("message", MensajesResponseRestGeneral.TEXTO_RESPONSE_FORBIDDEN+mensaje);

        return new ResponseEntity<>(resultado,HttpStatus.FORBIDDEN);
    }



    //500
    public static ResponseEntity<?> internalServerError(String mensaje){
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.FALSE);
        resultado.put("code",HttpStatus.INTERNAL_SERVER_ERROR.value());
        resultado.put("message", MensajesResponseRestGeneral.INTERNAL_SERVER_ERROR+", "+mensaje);

        return new ResponseEntity<>(
                resultado,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> internalServerError(){
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.FALSE);
        resultado.put("code",HttpStatus.INTERNAL_SERVER_ERROR.value());
        resultado.put("message", MensajesResponseRestGeneral.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(
                resultado,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public static ResponseEntity<?> solicitudIncorrecta(String ...errorList){
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.FALSE);
        resultado.put("code",HttpStatus.BAD_REQUEST.value());
        resultado.put("message", MensajesResponseRestGeneral.TEXTO_RESPONSE_BAD);
        resultado.put("errorList",errorList);
        return new ResponseEntity<>(
                resultado,
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> badRequestWithListaErrores(List<String> errores){
        Map<String, Object> resultado=new HashMap<>();
        resultado.put("success",Boolean.FALSE);
        resultado.put("code",HttpStatus.BAD_REQUEST.value());
        resultado.put("message", MensajesResponseRestGeneral.TEXTO_RESPONSE_BAD);
        resultado.put("errorList",errores);
        return new ResponseEntity<>(
                resultado,
                HttpStatus.BAD_REQUEST);
    }



    public static ResponseEntity<?> retornarErroresValidacion(BindingResult resultadoValidacion) {

        Map<String, Object> resultado=new HashMap<>();
        Map<String, Object> listError=new HashMap<>();

        if(resultadoValidacion.hasErrors())
        {
            List<String> errores= resultadoValidacion.getFieldErrors().stream()
                    .map(error->"Atributo:'"+error.getField()+"'. "+error.getDefaultMessage())
                    .collect(Collectors.toList());

            listError.put("errores", errores);

            resultado.put("success",Boolean.FALSE);
            resultado.put("code",HttpStatus.BAD_REQUEST.value());
            resultado.put("message",MensajesResponseRestGeneral.TEXTO_RESPUESTA_EXISTE_ERROR_PETICION);
            resultado.put("errorList",listError);
        }
        return new ResponseEntity<>(resultado,
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> getResponseGeneral(Boolean success,
                                                       HttpStatus code, String message, Object data) {
        Map<String, Object> resultado=new HashMap<>();

        resultado.put("success",success);
        resultado.put("code", code.value());
        resultado.put("message",message);
        resultado.put("data",data);
       // resultado.put("errorList",errorList);

        return new ResponseEntity<>(resultado,code);
    }


   /* public static ResponseEntity<?> internalServerError(String mensaje){
        return getResponseGeneral(false,HttpStatus.INTERNAL_SERVER_ERROR,mensaje,null,null);
    }*/
    public static  ResponseEntity<?>  evaluarExcepcionGeneral(Exception exp){
        return internalServerError(exp.getMessage());
    }

}

