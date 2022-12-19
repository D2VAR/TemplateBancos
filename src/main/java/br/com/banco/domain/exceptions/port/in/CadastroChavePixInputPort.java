package br.com.banco.domain.exceptions.port.in;

import br.com.banco.domain.dto.chave.ChavePixMensagem;
import br.com.banco.domain.dto.chave.ChavePixRequest;

public interface CadastroChavePixInputPort {
    void cadastrarChaveBacen(ChavePixRequest chavePixRequest);
    void cadastrarChaveInterna(ChavePixMensagem chavePix);

}
