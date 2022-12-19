package br.com.banco.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoPix {

    private String chaveDestino;
    private BigDecimal valor;
    private String codigoBanco;
    private String cpf;
    private String name;
}
