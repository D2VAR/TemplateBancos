package br.com.banco.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailRequest{
    private String from;
    private String to;
    private String subject;
    private String content;
    private String attachFileRoute;
}
