package br.com.banco.port.in;

import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixRequest;

public interface PixSender{

    void enviarPix(TransacaoPixRequest request);

    void concretizarPix(RetornoTransacaoPixMensagem retornoTransacaoPixMensagem);
}
