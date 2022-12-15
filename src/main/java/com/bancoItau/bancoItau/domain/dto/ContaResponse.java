package com.bancoItau.bancoItau.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaResponse{

    private String agencia;
    private Integer conta;
    private BigDecimal saldo;
}
