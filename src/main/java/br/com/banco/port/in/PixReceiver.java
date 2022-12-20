package br.com.banco.port.in;

import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixMensagem;

public interface PixReceiver{
    void validarTransacao(TransacaoPixMensagem transacaoPixMensagem);

    void consolidarRecebimentoPix(RetornoTransacaoPixMensagem retornoTransacaoPixMensagem);
}
