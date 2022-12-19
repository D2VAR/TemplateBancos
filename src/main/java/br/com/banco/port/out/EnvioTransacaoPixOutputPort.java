package br.com.banco.port.out;

import br.com.banco.domain.dto.transacao.TransacaoPixResponse;

public interface EnvioTransacaoPixOutputPort {
    void enviarPix(TransacaoPixResponse response);
}
