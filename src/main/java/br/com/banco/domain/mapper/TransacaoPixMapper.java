package br.com.banco.domain.mapper;

import br.com.banco.domain.dto.transacao.TransacaoPixRequest;
import br.com.banco.domain.dto.transacao.TransacaoPixResponse;
import br.com.banco.domain.dto.transacao.TransacaoValidacaoRequest;
import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;

public interface TransacaoPixMapper {
   TransacaoValidacaoResponse validacaoToResponse(TransacaoValidacaoRequest transacaoPixRequest);
    TransacaoPixResponse requestToResponse(TransacaoPixRequest transacaoPixRequest);
}
