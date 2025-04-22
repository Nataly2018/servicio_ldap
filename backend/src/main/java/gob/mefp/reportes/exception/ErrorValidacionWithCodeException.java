package gob.mefp.reportes.exception;

public class ErrorValidacionWithCodeException extends RuntimeException {

    String code;
    public ErrorValidacionWithCodeException(String message,String codeUser)
    {
        super(message);
        code = codeUser;
    }
}
