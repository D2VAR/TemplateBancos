package br.com.banco.domain.exceptions;

public class ContaPixNotFoundException extends RuntimeException {
    public ContaPixNotFoundException(String message) {
        super(message);
    }

}
