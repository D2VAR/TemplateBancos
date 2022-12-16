package com.itau.adapter.out.api.bacen.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.domain.enums.TipoChave;
import lombok.Getter;

@Getter
public class ApiBacenResponse{
    @JsonProperty("codigo_banco")
    private String codBanco;
    @JsonProperty("chave_existente")
    private Boolean chaveExists;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;
}