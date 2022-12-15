package com.bancoItau.bancoItau.domain.mapper;

import com.bancoItau.bancoItau.domain.dto.ChavePixResponse;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChavePixMapperImpl implements ChavePixMapper{

    @Override
    public ChavePixResponse chavePixToChavePixResponseDTO(ChavePix chavePix) {
         return new ChavePixResponse(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }


    @Override
    public List<ChavePixResponse> chavePixListToChavePixResponseDTOList(List<ChavePix> chavePixList) {
        return null;
    }


}
