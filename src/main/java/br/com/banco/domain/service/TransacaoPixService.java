package br.com.banco.domain.service;

import br.com.banco.adapter.out.db.repository.ChavePixRepository;
import br.com.banco.adapter.out.db.repository.ContaRepository;
import br.com.banco.domain.dto.transacao.TransacaoPixRequest;
import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.exceptions.ContaPixNotFoundException;
import br.com.banco.domain.mapper.TransacaoPixMapper;
import br.com.banco.domain.exceptions.port.in.ValidaTransacaoPixInputPort;
import br.com.banco.domain.exceptions.port.out.ValidaTransacaoPixOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransacaoPixService implements ValidaTransacaoPixInputPort{


    private final ValidaTransacaoPixOutputPort validaTransacaoPixOutputPort;


    private final TransacaoPixMapper mapper;
    private final ChavePixRepository repository;

    private final ContaRepository contaRepository;



    @Override
    public void validarTransacaoPix(TransacaoPixRequest request) {
        var response = mapper.requestToResponse(request);
        validaChave(response);
        validaConta(response);
        response.setPixRealizado(Boolean.TRUE);
        validaTransacaoPixOutputPort.enviarValidacaoPositiva(response);

        response.setContaEncontrada(Boolean.TRUE);
        validaTransacaoPixOutputPort.enviarValidacaoPositiva(response);


    }

    private void validaConta(TransacaoValidacaoResponse response) {
        var ContaExist = contaRepository.findByAgenciaAndNumeroConta(response.getChaveDestino(), response.getCodBancoOrigem());
        if (ContaExist.isEmpty()){
            response.setContaEncontrada(Boolean.FALSE);
            validaTransacaoPixOutputPort.enviarValidacaoNegativa(response);
            throw new ContaPixNotFoundException("Conta destino não encontrada. Falha na transaçao");
        }
    }


    private void validaChave(TransacaoValidacaoResponse response){
        var keyExists = repository.findByValor(response.getChaveDestino());
        if (keyExists.isEmpty()){
            response.setPixRealizado(Boolean.FALSE);
            validaTransacaoPixOutputPort.enviarValidacaoNegativa(response);
            throw new ChavePixNotFoundException("Chave destino não encontrada. Falha na transaçao");
        }
    }
}
