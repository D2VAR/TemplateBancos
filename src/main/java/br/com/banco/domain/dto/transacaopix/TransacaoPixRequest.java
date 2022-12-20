package br.com.banco.domain.dto.transacaopix;

import br.com.banco.domain.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoPixRequest{
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("tipo_chave_destino")
    private TipoChave tipoChave;
    @JsonProperty("valor_transferencia")
    private BigDecimal valor;
    @JsonProperty("id_conta_pagadora")
    private UUID idConta;
}


