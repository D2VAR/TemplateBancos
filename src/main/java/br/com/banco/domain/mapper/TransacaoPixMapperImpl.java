package br.com.banco.domain.mapper;

import br.com.banco.domain.dto.chave.ChavePixMensagem;
import br.com.banco.domain.dto.chave.ChavePixResponse;
import br.com.banco.domain.dto.transacao.TransacaoPixRequest;
import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;
import br.com.banco.domain.model.ChavePix;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransacaoPixMapperImpl implements TransacaoPixMapper{


    @Override
    public TransacaoValidacaoResponse requestToResponse(TransacaoPixRequest request) {
        return new TransacaoValidacaoResponse(request.getTransactionId(), request.getNome(), request.getCpfCnpj(), request.getChaveDestino(),
                request.getValor(), request.getCodBancoDestino());
    }
}
