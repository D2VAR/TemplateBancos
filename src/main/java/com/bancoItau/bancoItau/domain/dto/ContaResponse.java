package com.bancoItau.bancoItau.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaResponse{

    private String agencia;
    private Integer numeroConta;
    private BigDecimal saldo;
}
