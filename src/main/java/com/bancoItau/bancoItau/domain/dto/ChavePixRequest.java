package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.domain.enums.TipoChave;
import com.bancoItau.bancoItau.domain.model.Conta;
import com.bancoItau.bancoItau.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixRequest {
    @JsonProperty("dados_conta")
    private Conta conta;
    @JsonProperty("dados_usuario")
    private Usuario usuario;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;

}
