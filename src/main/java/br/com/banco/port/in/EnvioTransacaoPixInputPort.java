package br.com.banco.port.in;

import br.com.banco.domain.dto.transacao.TransacaoPixRequest;

public interface EnvioTransacaoPixInputPort {
    void enviarTransacaoPix(TransacaoPixRequest request);

}
