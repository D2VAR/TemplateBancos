package br.com.banco.port.out;

import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;

public interface ValidacaoTransacaoOutputPort {

    void notificaFalha(TransacaoValidacaoResponse response);

    void notificaSucesso(TransacaoValidacaoResponse response);
}
