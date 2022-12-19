package br.com.banco.domain.service;

import br.com.banco.domain.dto.transacao.TransacaoPixRequest;
import br.com.banco.domain.mapper.TransacaoPixMapper;
import br.com.banco.port.in.ValidaTransacaoPixInputPort;
import br.com.banco.port.out.ValidaTransacaoPixOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransacaoPixService implements ValidaTransacaoPixInputPort{
    private final ValidaTransacaoPixOutputPort validaTransacaoPixOutputPort;
    private final TransacaoPixMapper mapper;
    private final ChavePixService chavePixService;
    private final ContaService contaService;

    @Override
    public void validarTransacaoPix(TransacaoPixRequest request){
        try{
            var chavePix = chavePixService.findByValor(request.getChaveDestino());
            var conta = chavePix.get().getConta();
            contaService.getContaById(conta.getId());
            var response = mapper.requestToResponse(request);
            validaTransacaoPixOutputPort.enviarValidacaoPositiva(response);
        } catch (Exception e){
            var response = mapper.requestToResponse(request);
            validaTransacaoPixOutputPort.enviarValidacaoNegativa(response);
        }
    }
}
