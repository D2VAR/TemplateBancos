package br.com.banco.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaResponse{
    @JsonProperty("id_conta")
    private UUID id;
    @JsonProperty("agencia")
    private String agencia;
    @JsonProperty("numero_conta")
    private String numero;
    @JsonProperty("id_usuario")
    private UUID idUsuario;
}
