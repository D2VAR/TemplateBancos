package br.com.banco.domain.service;

import br.com.banco.adapter.out.db.repository.TransacaoPixRepository;
import br.com.banco.domain.dto.TransacaoPixRequest;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.TransacaoPix;
import br.com.banco.port.out.TransacaoBacenProducerOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransacaoPixService {

    private final TransacaoPixRepository transacaoPixRepository;
    private final ContaService contaService;

    private final TransacaoBacenProducerOutputPort transacaoBacenProducerOutputPort;
    private final ChavePixService chavePixService;


    public void enviaPix(TransacaoPixRequest transacaoPixRequest) {
        TransacaoPix transacaoPix = transacaoPixRequestToModel(transacaoPixRequest);
        chavePixService.validarExistenciaChaveDestinoPixBacen(transacaoPixRequest);
        transacaoBacenProducerOutputPort.enviarMensagemTransacaoPix(transacaoPix);

    }

    private TransacaoPix transacaoPixRequestToModel(TransacaoPixRequest transacaoPixRequest) {
        var conta = contaService.getContaById(transacaoPixRequest.getIdConta());
        return buildTransacaoPix(transacaoPixRequest.getChaveDestino(), transacaoPixRequest.getValor(), conta);
    }


    private TransacaoPix buildTransacaoPix(String chaveDestino, BigDecimal valor, Conta conta) {
        return new TransacaoPix(chaveDestino, valor, conta);
    }


}