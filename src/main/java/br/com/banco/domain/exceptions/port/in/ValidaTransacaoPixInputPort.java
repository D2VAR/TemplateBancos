package br.com.banco.domain.exceptions.port.in;

import br.com.banco.domain.dto.transacao.TransacaoPixRequest;

public interface ValidaTransacaoPixInputPort {
    void validarTransacaoPix(TransacaoPixRequest request);

}
