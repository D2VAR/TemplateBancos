package com.example.pixitau.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ContaResquest {
    private UUID id;

    private int agencia;

    private String numero;

    private BigDecimal saldo;
}
