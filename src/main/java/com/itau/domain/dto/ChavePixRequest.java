package com.itau.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.domain.enums.TipoChave;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChavePixRequest {
    @JsonProperty("id_conta")
    private UUID idConta;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;

}
