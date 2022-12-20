package br.com.banco.domain.dto;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Usuario;
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

    public ContaResponse(Conta conta){
        this(conta.getUsuario());
        this.id = conta.getId();
        this.agencia = conta.getAgencia();
        this.numero = conta.getNumero();

    }

    private ContaResponse(Usuario usuario){
        this.idUsuario = usuario.getId();
    }
}
