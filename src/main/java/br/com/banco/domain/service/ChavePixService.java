package br.com.banco.domain.service;

import br.com.banco.adapter.out.api.bacen.ApiBacen;
import br.com.banco.adapter.out.db.repository.ChavePixRepository;
import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.domain.dto.ChavePixRequest;
import br.com.banco.domain.dto.ChavePixResponse;
import br.com.banco.domain.exceptions.ChavePixAlreadyExistException;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.model.ChavePix;
import br.com.banco.port.in.CadastroChavePixInput;
import br.com.banco.port.out.CadastroChavePixOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChavePixService implements CadastroChavePixInput{
    private final ChavePixRepository chavePixRepository;
    private final ApiBacen apiBacen;
    private final ContaService contaService;
    private final CadastroChavePixOutput cadastroChavePixOutput;

    @Override
    public void cadastrarChaveBacen(ChavePixRequest chavePixRequest){
        var entity = chavePixRequestToModel(chavePixRequest);
        validarExistenciaChavePixBacen(chavePixRequest);
        cadastroChavePixOutput.enviarMensagemCadastroChave(entity);
    }

    private ChavePix chavePixRequestToModel(ChavePixRequest chavePixRequest){
        var conta = contaService.getById(chavePixRequest.getIdConta());
        return new ChavePix(chavePixRequest.getValorChave(), chavePixRequest.getTipoChave(), conta);
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
    }

    private ChavePix chavePixMensagemToModel(ChavePixMensagem chavePixMensagem){
        var conta = contaService.getByAgenciaAndNumero(chavePixMensagem.getAgenciaConta(), chavePixMensagem.getNumeroConta());
        return new ChavePix(chavePixMensagem.getValorChave(), chavePixMensagem.getTipoChave(), conta);
    }

    private void validarExistenciaChavePixInterna(String valor){
        if (chavePixRepository.findByValor(valor).isPresent())
            throw new ChavePixAlreadyExistException("Chave Pix ja existente!");
    }

    public ChavePixResponse getByValor(String valor){
        var chavePix = chavePixRepository.findByValor(valor).orElseThrow(
                () -> new ChavePixNotFoundException("Chave Pix não encontrada!"));
        return new ChavePixResponse(chavePix);
    }

    public ChavePixResponse save(ChavePix chavePix){
        chavePixRepository.save(chavePix);
        return new ChavePixResponse(chavePix);
    }
}
