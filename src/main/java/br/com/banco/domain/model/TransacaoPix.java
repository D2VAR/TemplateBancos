package br.com.banco.domain.model;

import br.com.banco.domain.enums.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class TransacaoPix{

    private TipoChave tipoChave;
    private String chaveDestino;
    private BigDecimal valor;
    private String codigoBanco;
    private String cpf;
    private String name;

    public TransacaoPix(String chaveDestino, TipoChave tipoChave, BigDecimal valor, Conta conta){
        this(conta.getUsuario());
        this.chaveDestino = chaveDestino;
        this.valor = valor;
        this.codigoBanco = conta.getBanco();
        this.tipoChave = tipoChave;
    }

    private TransacaoPix(Usuario usuario){
        this.cpf = usuario.getCpf();
        this.name = usuario.getNome();
    }
}
