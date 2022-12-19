package br.com.banco.domain.exceptions.handler;

import br.com.banco.domain.exceptions.ChavePixAlreadyExistException;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.exceptions.ContaNotFoundException;
import br.com.banco.domain.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({ChavePixNotFoundException.class, ContaNotFoundException.class, UsuarioNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex){
        return buildResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChavePixAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(RuntimeException ex){
        return buildResponseEntity(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
        return buildResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(Exception exception, HttpStatus status){
        var errorResponse = buildErrorResponse(exception, status);
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(Exception e, HttpStatus status){
        return new ErrorResponse(e.getMessage(), status);
    }

}
