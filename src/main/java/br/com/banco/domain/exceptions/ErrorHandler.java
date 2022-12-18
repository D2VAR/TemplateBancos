package br.com.banco.domain.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
@ControllerAdvice
@AllArgsConstructor

public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ChavePixAlreadyExistException.class)
    public ResponseEntity<Object> handleConflitExcepiton(RuntimeException ex, WebRequest request) {

        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(ChavePixNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundExcepiton(RuntimeException ex, WebRequest request) {

        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        String userMessage = ex.getMessage();
        String developerMessage = ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));

        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private static class Error{

        private final String userMessage;
        private final String developerMessage;

        public Error(String userMessage, String developerMessage) {

            this.userMessage = userMessage;
            this.developerMessage = developerMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

    }
}
