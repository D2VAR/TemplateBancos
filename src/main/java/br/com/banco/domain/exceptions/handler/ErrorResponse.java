package br.com.banco.domain.exceptions.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse{
    private final LocalDateTime timestamp;
    private final Integer status;
    private final String message;

    public ErrorResponse(String message, HttpStatus status){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status.value();
    }

}