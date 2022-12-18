package br.com.banco.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.model.Conta;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChavePixMensagem {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("codigo_banco")
    private String codBanco;
    @JsonProperty("numero_conta")
    private String numeroConta;
    @JsonProperty("agencia_conta")
    private String agenciaConta;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;

    public ChavePixMensagem(String valor, TipoChave tipo, Conta conta) {
        this.valorChave = valor;
        this.tipoChave = tipo;
        this.numeroConta = conta.getNumeroConta();
        this.agenciaConta = conta.getAgencia();
        this.cpfCnpj = conta.getUsuario().getCpf();
        this.nome = conta.getUsuario().getNome();
        this.codBanco = conta.getBanco();

    }
}
