package br.com.banco.port.in;

import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.domain.dto.ChavePixRequest;

public interface CadastroChavePixInputPort {
    void cadastrarChaveBacen(ChavePixRequest chavePixRequest);
    void cadastrarChaveInterna(ChavePixMensagem chavePix);

}
