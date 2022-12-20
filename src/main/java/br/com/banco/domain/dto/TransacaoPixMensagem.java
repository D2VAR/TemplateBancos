package br.com.banco.domain.dto;

import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.TransacaoPix;
import br.com.banco.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransacaoPixMensagem {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("codigo_banco_origem")
    private String codBancoOrigem;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("valor")
    private BigDecimal valor;


    public TransacaoPixMensagem(TransacaoPix transacaoPix) {
        this.chaveDestino = getChaveDestino();
        this.valor = getValor();
    }

    private TransacaoPixMensagem(Conta conta) {
        this(conta.getUsuario());
        this.codBancoOrigem = conta.getBanco();

    }

    private TransacaoPixMensagem(Usuario usuario) {
        this.cpfCnpj = usuario.getCpf();
        this.nome = usuario.getNome();
    }


}
