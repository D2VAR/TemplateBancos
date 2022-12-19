package br.com.banco.port.in;

import br.com.banco.domain.dto.ChavePixMensagem;

public interface NotificationInputPort{
    void sendSuccessChavePixCadastradaEmail(ChavePixMensagem mensagem);

    void sendFailureChavePixCadastradaEmail(ChavePixMensagem mensagem);
}
