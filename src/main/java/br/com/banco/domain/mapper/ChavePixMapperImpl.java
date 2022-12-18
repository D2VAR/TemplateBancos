package br.com.banco.domain.mapper;

import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.domain.dto.ChavePixResponse;
import br.com.banco.domain.model.ChavePix;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChavePixMapperImpl implements ChavePixMapper{

    @Override
    public ChavePixResponse toResponse(ChavePix chavePix){
        return new ChavePixResponse(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }

    @Override
    public List<ChavePixResponse> toResponseList(List<ChavePix> chavePixList){
        return chavePixList.stream().map(this::toResponse).toList();
    }

    @Override
    public ChavePix toModel(ChavePixMensagem chavePixMensagem){
        return null;
    }

    @Override
    public ChavePixMensagem toMensagem(ChavePix novaChavePix){
               return new ChavePixMensagem(novaChavePix.getValor(), novaChavePix.getTipo(), novaChavePix.getConta());
    }
}
