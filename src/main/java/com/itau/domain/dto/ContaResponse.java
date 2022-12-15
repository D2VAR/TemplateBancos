package com.itau.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaResponse{

    private String agencia;
    private String numeroConta;
    private BigDecimal saldo;
}
