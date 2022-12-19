package br.com.banco.port.in;

import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.domain.dto.ChavePixRequest;
import br.com.banco.domain.dto.TransacaoPixMensagem;

public interface TransacaoPixInputPort {

    void validarTransacao(TransacaoPixMensagem transacaoPixMensagem);

}
