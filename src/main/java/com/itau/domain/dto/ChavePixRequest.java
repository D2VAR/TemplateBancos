package com.itau.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.domain.enums.TipoChave;
import com.itau.domain.model.Conta;
import com.itau.domain.model.Usuario;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixRequest {
    @JsonProperty("id_conta")
    private UUID idConta;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;

}
