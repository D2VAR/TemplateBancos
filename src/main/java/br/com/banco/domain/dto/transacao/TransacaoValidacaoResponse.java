package br.com.banco.domain.dto.transacao;

import br.com.banco.domain.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoValidacaoResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("pix_realizado")
    private Boolean pixRealizado;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("valor_transferencia")
    private BigDecimal valor;
    @JsonProperty("codigo_banco_destino")
    private String codBancoDestino;

    public TransacaoValidacaoResponse(String transactionId, String nome, String cpfCnpj, String chaveDestino, BigDecimal valor, String codBancoDestino) {
        this.transactionId = transactionId;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.chaveDestino = chaveDestino;
        this.valor = valor;
        this.codBancoDestino = codBancoDestino;
    }
}
