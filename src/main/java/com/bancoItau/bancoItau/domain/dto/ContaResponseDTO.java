package com.bancoItau.bancoItau.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContaResponseDTO {
    private UUID id;
    private String agencia;
    private Integer nconta;
    private BigDecimal saldo;
}
