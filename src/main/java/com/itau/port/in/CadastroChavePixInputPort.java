package com.itau.port.in;

import com.itau.domain.dto.ChavePixMensagem;
import com.itau.domain.dto.ChavePixRequest;

public interface CadastroChavePixInputPort {
    void cadastrarChaveBacen(ChavePixRequest chavePixRequest);
    void cadastrarChaveInterna(ChavePixMensagem chavePix);

}
