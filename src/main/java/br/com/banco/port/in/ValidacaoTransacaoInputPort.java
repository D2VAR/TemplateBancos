package br.com.banco.port.in;

import br.com.banco.domain.dto.transacao.TransacaoValidacaoRequest;

public interface ValidacaoTransacaoInputPort {


    void retornarSucesso(TransacaoValidacaoRequest request);

    void retornarFalha(TransacaoValidacaoRequest request);
}
