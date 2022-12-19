package br.com.banco.domain.dto.chave;

import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.model.ChavePix;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChavePixMensagem{
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

    public ChavePixMensagem(ChavePix chavePix){
        this(chavePix.getConta());
        this.valorChave = chavePix.getValor();
        this.tipoChave = chavePix.getTipo();
    }

    private ChavePixMensagem(Conta conta){
        this(conta.getUsuario());
        this.numeroConta = conta.getNumero();
        this.agenciaConta = conta.getAgencia();
        this.codBanco = conta.getBanco();

    }

    private ChavePixMensagem(Usuario usuario){
        this.cpfCnpj = usuario.getCpf();
        this.nome = usuario.getNome();
    }


}
