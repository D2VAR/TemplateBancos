package br.com.banco.domain.dto.transacaopix;

import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.model.TransacaoPix;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoPixMensagem {
    @JsonProperty("transaction_id")
    private String transactionId;
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

    public TransacaoPixMensagem(TransacaoPix pix) {
        this.nome = pix.getName();
        this.cpfCnpj = pix.getCpf();
        this.tipoChave = pix.getTipoChave();
        this.chaveDestino = pix.getChaveDestino();
        this.valor = pix.getValor();
        this.codBancoOrigem = pix.getCodigoBanco();
    }
}
