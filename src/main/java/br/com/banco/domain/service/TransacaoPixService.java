package br.com.banco.domain.service;

import br.com.banco.domain.dto.transacao.TransacaoPixRequest;
import br.com.banco.domain.dto.transacao.TransacaoValidacaoRequest;
import br.com.banco.domain.mapper.TransacaoPixMapper;
import br.com.banco.port.in.EnvioTransacaoPixInputPort;
import br.com.banco.port.in.ValidacaoTransacaoInputPort;
import br.com.banco.port.out.EnvioTransacaoPixOutputPort;
import br.com.banco.port.out.ValidacaoTransacaoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransacaoPixService implements EnvioTransacaoPixInputPort, ValidacaoTransacaoInputPort {


    private final EnvioTransacaoPixOutputPort envioTransacaoPixOutputPort;
    private final ValidacaoTransacaoOutputPort validacaoOutputPort;

    private final TransacaoPixMapper mapper;

    @Override
    public void enviarTransacaoPix(TransacaoPixRequest request) {

        var response = mapper.requestToResponse(request);
        envioTransacaoPixOutputPort.enviarPix(response);
    }

    @Override
    public void retornarSucesso(TransacaoValidacaoRequest request) {
        var response = mapper.validacaoToResponse(request);
        validacaoOutputPort.notificaSucesso(response);
    }

    @Override
    public void retornarFalha(TransacaoValidacaoRequest request) {
        var response = mapper.validacaoToResponse(request);
        validacaoOutputPort.notificaFalha(response);
    }

}
