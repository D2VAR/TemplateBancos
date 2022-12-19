package br.com.banco.domain.dto.conta;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaRequest{
    @JsonProperty("agencia")
    private String agencia;
    @JsonProperty("id_usuario")
    private UUID idUsuario;
}
