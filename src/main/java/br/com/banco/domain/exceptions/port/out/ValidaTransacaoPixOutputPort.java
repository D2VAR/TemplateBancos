package br.com.banco.domain.exceptions.port.out;

import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;

public interface ValidaTransacaoPixOutputPort {

    void enviarValidacaoPositiva(TransacaoValidacaoResponse response);

    void enviarValidacaoNegativa(TransacaoValidacaoResponse response);
}
