package br.com.banco.domain.dto.transacaopix;

import br.com.banco.domain.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RetornoTransacaoPixMensagem{
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("pix_realizado")
    private boolean pixRealizado;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("valor_transferencia")
    private BigDecimal valor;
    @JsonProperty("codigo_banco_origem")
    private String codBancoOrigem;

    public RetornoTransacaoPixMensagem(TransacaoPixMensagem mensagem){
        this.transactionId = mensagem.getTransactionId();
        this.nome = mensagem.getNome();
        this.cpfCnpj = mensagem.getCpfCnpj();
        this.tipoChave = mensagem.getTipoChave();
        this.chaveDestino = mensagem.getChaveDestino();
        this.valor = mensagem.getValor();
        this.codBancoOrigem = mensagem.getCodBancoOrigem();
    }

}
