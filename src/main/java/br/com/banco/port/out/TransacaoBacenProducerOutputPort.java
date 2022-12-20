package br.com.banco.port.out;


import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.model.TransacaoPix;

public interface TransacaoBacenProducerOutputPort{
    void enviarMensagemTransacaoPix(TransacaoPix mensagem);

    void enviarMensagemErroValidacaoPix(RetornoTransacaoPixMensagem mensagem);

    void enviarMensagemSucessoValidacaoPix(RetornoTransacaoPixMensagem mensagem);
}
