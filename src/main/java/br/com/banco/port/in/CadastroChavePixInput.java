package br.com.banco.port.in;

import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.dto.chavepix.ChavePixRequest;

public interface CadastroChavePixInput{
    void cadastrarChaveBacen(ChavePixRequest chavePixRequest);

    void cadastrarChaveInterna(ChavePixMensagem chavePix);

}
