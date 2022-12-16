package com.itau.domain.mapper;

import com.itau.domain.dto.ChavePixMensagem;
import com.itau.domain.dto.ChavePixResponse;
import com.itau.domain.model.ChavePix;
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
