package br.com.banco.port.in;

import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;

public interface NotificationInputPort{
    void sendSuccessChavePixCadastradaEmail(ChavePixMensagem mensagem);

    void sendFailureChavePixCadastradaEmail(ChavePixMensagem mensagem);

    void sendSuccessTransacaoPixEmail(RetornoTransacaoPixMensagem mensagem);

    void sendFailureTransacaoPixEmail(RetornoTransacaoPixMensagem mensagem);

    void sendSuccessReceivePixEmail(RetornoTransacaoPixMensagem mensagem);
}

