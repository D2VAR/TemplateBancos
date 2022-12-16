package com.itau.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaResponse{

    @JsonProperty("agencia")
    private String agencia;
    @JsonProperty("numero_conta")
    private String numeroConta;
    @JsonProperty("saldo")
    private BigDecimal saldo;
    @JsonProperty("id_usuario")
    private UUID idUsuario;
}
