package br.com.banco.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class TransacaoPix {

    private String chaveDestino;
    private BigDecimal valor;
    private String codigoBanco;
    private String cpf;
    private String name;

    public TransacaoPix(String chaveDestino, BigDecimal valor, Conta conta) {
        this.chaveDestino = chaveDestino;
        this.valor = valor;
        this.codigoBanco = conta.getBanco();
        this.cpf = conta.getUsuario().getCpf();
        this.name = conta.getUsuario().getNome();
    }
}
