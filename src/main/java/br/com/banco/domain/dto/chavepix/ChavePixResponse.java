package br.com.banco.domain.dto.chavepix;

import br.com.banco.domain.model.ChavePix;
import br.com.banco.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class ChavePixResponse{
    private UUID id;
    private String valor;
    private Conta conta;

    public ChavePixResponse(ChavePix chavePix){
        this.id = chavePix.getId();
        this.valor = chavePix.getValor();
        this.conta = chavePix.getConta();
    }

}
