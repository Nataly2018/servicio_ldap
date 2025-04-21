package gob.mefp.reportes.exception;

import gob.ypfb.nova.commons.dto.ErrorRestResponse;
import gob.ypfb.nova.commons.dto.ResponseRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ResponseBody
    @ExceptionHandler(ErrorValidacionException.class)
    ResponseEntity<?> handleErrorValidacionTextoException(ErrorValidacionException exp) {
        log.error("Parametro Incorrecto");
        return ErrorRestResponse.badRequest(exp.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ErrorValidacionWithCodeException.class)
    ResponseEntity<?> handleErrorValidacionWithCodeExceptionTextoException(ErrorValidacionWithCodeException exp) {
        log.error("Parametro Incorrecto");
        return ErrorRestResponse.badRequestWithCode(exp.getMessage(),exp.code);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Manejo de Excepción Interna, Ocurrio un error desconocido, ", ex);
        return (ResponseEntity<Object>) ErrorRestResponse.badRequest(ex.getMessage());

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleAllUncaughtException(Exception exception) {
        log.error("Ocurrio un error , ", exception);
        return ResponseRest.internalServerError( exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(IntegridadYpfbException.class)
    ResponseEntity<?> handleIntegridadException(IntegridadYpfbException exp){
        log.error("Excepcion integridad",exp);
        //vIServiceLogRegistro.registroHistorial(exp);
        return ResponseRest.internalServerError(exp.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(ListaErroresYpfbException.class)
    ResponseEntity<?> handleListaErroresException(ListaErroresYpfbException exp){
        //log.error("Excepcion generica");
        //vIServiceLogRegistro.registroHistorial(exp);
        return ResponseRest.badRequestWithListaErrores(exp.getListaErrores());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Error de validacion ", ex);
        return (ResponseEntity<Object>) ErrorRestResponse.badRequest(ex.getBindingResult());
    }


    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<?> handleNotFoundYpfbException(DataIntegrityViolationException exp){
        log.error("Error DataIntegrityViolationException ",exp);
        return ResponseRest.internalServerError(exp.getMessage());
    }

// RecursoNoEncontradoException

    @ResponseBody
    @ExceptionHandler(RecursoNoEncontradoException.class)
    ResponseEntity<?> handleRecursoNoEncontradoException(RecursoNoEncontradoException exp) {
        //log.error("Parametro Incorrecto");
        return ErrorRestResponse.noEncontrado(exp.getMessage());
    }


}
