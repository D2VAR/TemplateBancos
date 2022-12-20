package br.com.banco.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoPixRequest {

    private String chaveDestino;
    private BigDecimal valor;
    private UUID idConta;
}


