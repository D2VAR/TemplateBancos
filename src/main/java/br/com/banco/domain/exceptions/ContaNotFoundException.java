package br.com.banco.domain.exceptions;

public class ContaNotFoundException extends RuntimeException{
    public ContaNotFoundException(String message){
        super(message);
    }

}
