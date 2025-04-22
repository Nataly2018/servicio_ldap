package gob.mefp.reportes.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static gob.mefp.reportes.config.MensajesResponseRestGeneral.TEXTO_RESPUESTA_OPERACION_CORRECTA;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class RespuestaGeneral {

    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    public static ResponseEntity<?> ok(Object data) {
        return getResponseGeneral(true, HttpStatus.OK, TEXTO_RESPUESTA_OPERACION_CORRECTA, data, null);
    }

    public static ResponseEntity<?> okWithOutContent() {
        return getResponseGeneral(true, HttpStatus.OK, TEXTO_RESPUESTA_OPERACION_CORRECTA, "Datos Registrados", null);
    }

    public static ResponseEntity<?> created(Object data) {
        return getResponseGeneral(true, HttpStatus.CREATED, TEXTO_RESPUESTA_OPERACION_CORRECTA, data, null);
    }

    public static ResponseEntity<?> createdWithContent(Object data) {
        return getResponseGeneral(true, HttpStatus.CREATED, TEXTO_RESPUESTA_OPERACION_CORRECTA, data, null);
    }

    public static ResponseEntity<?> getResponseGeneral(Boolean success,
                                                       HttpStatus code, String message, Object data, List<String> errores) {
        //log.info("El mensaje es " + message + " y el codigo es " + code.value());
        RespuestaGeneral responseRestGeneral = new RespuestaGeneral();
        responseRestGeneral.success = success;
        responseRestGeneral.code = code.value();
        responseRestGeneral.message = message;
        responseRestGeneral.data = data;

        return new ResponseEntity<>(
                responseRestGeneral,
                code);
    }

    public static ResponseEntity<?> okAccepted(String pTexto) {
        return getResponseGeneral(true, HttpStatus.ACCEPTED, pTexto, null, null);
    }

    public static ResponseEntity<?> okNonAuthorize(String pTexto) {
        return getResponseGeneral(true, HttpStatus.NON_AUTHORITATIVE_INFORMATION, pTexto, null, null);
    }

    public static ResponseEntity<?> okPartialContent(String pTexto) {
        return getResponseGeneral(true, HttpStatus.PARTIAL_CONTENT, pTexto, null, null);
    }
}
