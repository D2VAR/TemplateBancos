package br.com.banco.adapter.out.api.bacen.dto;

import br.com.banco.domain.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiBacenResponse{
    @JsonProperty("codigo_banco")
    private String codBanco;
    @JsonProperty("chave_existente")
    private boolean chaveExists;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;
}
