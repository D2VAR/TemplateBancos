package br.com.banco.domain.dto;

import br.com.banco.domain.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecebedorPixMensagem {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("codigo_banco_origem")
    private String codBanco;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("valor_transferencia")
    private String valorTransferencia;
    @JsonProperty("pix_realizado")
    private Boolean pixRealizado;


}