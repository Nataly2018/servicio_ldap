package gob.mefp.reportes.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ErrorRespuestaGeneral {

    private Boolean success;
    private Integer code;
    private String message;
    private Object errorList;

    public static ResponseEntity<?> badRequest(String mensaje) {
        return getResponseGeneral(false, HttpStatus.BAD_REQUEST, mensaje, null, obtenerErrorComoListaErrores(mensaje));
    }

    public static ResponseEntity<?> badRequestWithErroresBindingResult(BindingResult resultadoValidacion) {
        List<String> errores = obtenerListaErrores(resultadoValidacion);
        return getResponseGeneral(false, HttpStatus.BAD_REQUEST, "Existen errores en la petición.", null, errores);
    }

    public static ResponseEntity<?> badRequestWithErroresTexto(List<String> errores) {
        return getResponseGeneral(false, HttpStatus.BAD_REQUEST, "Existen errores en la petición.", null, errores);
    }

    public static ResponseEntity<?> getResponseGeneral(Boolean success,
                                                       HttpStatus code, String message, Object data, Object errorList) {
        ErrorRespuestaGeneral errorResponseGeneral = new ErrorRespuestaGeneral();
        errorResponseGeneral.success = success;
        errorResponseGeneral.code = code.value();
        errorResponseGeneral.message = message;
        errorResponseGeneral.errorList = errorList;
        return new ResponseEntity<>(errorResponseGeneral, code);
    }

    public static ResponseEntity<?> retornarErroresValidacion(BindingResult resultadoValidacion) {
        ErrorRespuestaGeneral errorResponseGeneral = new ErrorRespuestaGeneral();
        Map<String, Object> resultado = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = resultadoValidacion.getFieldErrors().stream()
                    .map(error -> "Atributo:'" + error.getField() + "'. " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            resultado.put("errores", errores);
            errorResponseGeneral.setSuccess(Boolean.TRUE);
            errorResponseGeneral.setCode(HttpStatus.BAD_REQUEST.value());
            errorResponseGeneral.setMessage("Existen errores en la petición.");
            //errorResponseGeneral.setData(null);
            errorResponseGeneral.setErrorList(errores);
        }
        return new ResponseEntity<>(
                errorResponseGeneral,
                HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> internalServerError(String mensaje) {
        return getResponseGeneral(false, HttpStatus.INTERNAL_SERVER_ERROR, mensaje, null, obtenerErrorComoListaErrores(mensaje));
    }

    public static ResponseEntity<?> internalServerErrorDetalle(String mensaje, Object data) {
        return getResponseGeneral(false, HttpStatus.INTERNAL_SERVER_ERROR, mensaje, data, null);
    }

    public static ResponseEntity<?> evaluarExcepcionGeneral(Exception exp) {
        return internalServerError(exp.getMessage());
    }

    public static List<String> obtenerListaErrores(BindingResult resultadoValidacion) {
        return resultadoValidacion.getFieldErrors().stream()
                .map(error -> " " + error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());

    }

    public static List<String> obtenerErrorComoListaErrores(String mensaje) {
        List<String> errores = new ArrayList<>();
        errores.add(mensaje);
        return errores;
    }

    public static ResponseEntity<?> badRequestUnauthorized(String mensaje) {
        return getResponseGeneral(false, HttpStatus.UNAUTHORIZED, mensaje, null, obtenerErrorComoListaErrores(mensaje));
    }

    public static ResponseEntity<?> badRequestPaymentRequire(String mensaje) {
        return getResponseGeneral(false, HttpStatus.PAYMENT_REQUIRED, mensaje, null, obtenerErrorComoListaErrores(mensaje));
    }

    public static ResponseEntity<?> badRequestNotAcceptable(String mensaje) {
        return getResponseGeneral(false, HttpStatus.NOT_ACCEPTABLE, mensaje, null, obtenerErrorComoListaErrores(mensaje));
    }
}
