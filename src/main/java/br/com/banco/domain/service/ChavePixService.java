package br.com.banco.domain.service;

import br.com.banco.adapter.out.api.bacen.ApiBacen;
import br.com.banco.adapter.out.db.repository.ChavePixRepository;
import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.domain.dto.ChavePixRequest;
import br.com.banco.domain.dto.ChavePixResponse;
import br.com.banco.domain.exceptions.ChavePixAlreadyExistException;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.model.ChavePix;
import br.com.banco.port.in.CadastroChavePixInputPort;
import br.com.banco.port.out.BacenProducerOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        var entity = buildChavePixEntity(chavePixRequest);
        validarExistenciaChavePixBacen(chavePixRequest);
        bacenProducerOutputPort.enviarMensagemCadastroChave(entity);
    }

    private ChavePix buildChavePixEntity(ChavePixRequest chavePixRequest){
        var conta = contaService.getContaById(chavePixRequest.getIdConta());
        return new ChavePix(chavePixRequest.getValorChave(), chavePixRequest.getTipoChave(), conta);
    }

    private ChavePix buildChavePixEntity(ChavePixMensagem chavePixMensagem){
        var conta = contaService.getContaByAgenciaAndNumero(chavePixMensagem.getAgenciaConta(), chavePixMensagem.getNumeroConta());
        return new ChavePix(chavePixMensagem.getValorChave(), chavePixMensagem.getTipoChave(), conta);
    }

    private void validarExistenciaChavePixBacen(ChavePixRequest chavePixRequest){
        var responseApiBacen = apiBacen.chavePixExists(chavePixRequest.getValorChave());
        log.info("retorno bacen", responseApiBacen);
        if (responseApiBacen.isChaveExists())
            throw new RuntimeException("Chave Pix ja existente!");

    }

    @Override
    public void cadastrarChaveInterna(ChavePixMensagem chavePix){
        validarExistenciaChavePixInterna(chavePix.getValorChave());
        var entity = buildChavePixEntity(chavePix);
        save(entity);
        //TODO: enviar notificacao ao cliente
    }

    private void validarExistenciaChavePixInterna(String valor){
        try{
            findByValor(valor);
            throw new ChavePixAlreadyExistException("Chave ja existente!");
        } catch (ChavePixNotFoundException e){
        }
    }

    public ChavePixResponse save(ChavePix chavePix){
        chavePixRepository.save(chavePix);
        return new ChavePixResponse(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }

    public void delete(UUID chavePixId){
        ChavePix chavePix = findById(chavePixId);
        chavePixRepository.delete(chavePix);
    }

    public ChavePix findById(UUID chavePixId){
        return chavePixRepository.findById(chavePixId)
                .orElseThrow(() -> new RuntimeException("Chave Pix não encontrada!"));
    }

    public ChavePix findByValor(String valor){
        return chavePixRepository.findByValor(valor)
                .orElseThrow(() -> new ChavePixNotFoundException("Chave Pix não encontrada!"));
    }

    public List<ChavePix> findAll(){
        return chavePixRepository.findAll();
    }

}
