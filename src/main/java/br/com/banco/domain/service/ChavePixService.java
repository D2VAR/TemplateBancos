package br.com.banco.domain.service;

import br.com.banco.adapter.out.api.bacen.ApiBacen;
import br.com.banco.adapter.out.db.repository.ChavePixRepository;
import br.com.banco.domain.dto.chave.ChavePixMensagem;
import br.com.banco.domain.dto.chave.ChavePixRequest;
import br.com.banco.domain.dto.chave.ChavePixResponse;
import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.exceptions.ChavePixAlreadyExistException;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.model.ChavePix;
import br.com.banco.domain.model.Conta;
import br.com.banco.port.in.CadastroChavePixInputPort;
import br.com.banco.port.out.BacenProducerOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChavePixService implements CadastroChavePixInputPort{
    private final ChavePixRepository chavePixRepository;
    private final ApiBacen apiBacen;
    private final ContaService contaService;
    private final BacenProducerOutputPort bacenProducerOutputPort;

    @Override
    public void cadastrarChaveBacen(ChavePixRequest chavePixRequest){
        var entity = chavePixRequestToModel(chavePixRequest);
        validarExistenciaChavePixBacen(chavePixRequest);
        bacenProducerOutputPort.enviarMensagemCadastroChave(entity);
    }

    private ChavePix chavePixRequestToModel(ChavePixRequest chavePixRequest){
        var conta = contaService.getContaById(chavePixRequest.getIdConta());
        return buildChavePix(chavePixRequest.getValorChave(), chavePixRequest.getTipoChave(), conta);
    }

    private ChavePix buildChavePix(String valorChave, TipoChave tipoChave, Conta conta){
        return new ChavePix(valorChave, tipoChave, conta);
    }

    private void validarExistenciaChavePixBacen(ChavePixRequest chavePixRequest){
        var responseApiBacen = apiBacen.chavePixExists(chavePixRequest.getValorChave());
        log.info("# Retorno API Bacen: {}", responseApiBacen);
        if (responseApiBacen.isChaveExists())
            throw new ChavePixAlreadyExistException("Chave Pix ja existente!");

    }

    @Override
    public void cadastrarChaveInterna(ChavePixMensagem chavePix){
        validarExistenciaChavePixInterna(chavePix.getValorChave());
        var entity = chavePixMensagemToModel(chavePix);
        save(entity);
        //TODO: enviar notificacao ao cliente
    }

    private void validarExistenciaChavePixInterna(String valor){
        if (findByValor(valor).isPresent())
            throw new ChavePixAlreadyExistException("Chave Pix ja existente!");
    }

    public Optional<ChavePix> findByValor(String valor){
        return chavePixRepository.findByValor(valor);
    }

    private ChavePix chavePixMensagemToModel(ChavePixMensagem chavePixMensagem){
        var conta = contaService.getContaByAgenciaAndNumero(chavePixMensagem.getAgenciaConta(), chavePixMensagem.getNumeroConta());
        return buildChavePix(chavePixMensagem.getValorChave(), chavePixMensagem.getTipoChave(), conta);
    }

    public ChavePixResponse save(ChavePix chavePix){
        chavePixRepository.save(chavePix);
        return new ChavePixResponse(chavePix);
    }

    public ChavePix findById(UUID chavePixId){
        return chavePixRepository.findById(chavePixId)
                .orElseThrow(() -> new ChavePixNotFoundException("Chave Pix não encontrada!"));
    }

}
