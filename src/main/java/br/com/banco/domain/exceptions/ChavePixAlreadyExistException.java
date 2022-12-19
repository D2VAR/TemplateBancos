package br.com.banco.domain.exceptions;

public class ChavePixAlreadyExistException extends RuntimeException {
    public ChavePixAlreadyExistException(String message) {
        super(message);
    }

}
