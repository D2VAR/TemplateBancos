package com.itau.domain.mapper;

import com.itau.domain.dto.ChavePixResponse;
import com.itau.domain.model.ChavePix;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChavePixMapperImpl implements ChavePixMapper{

    @Override
    public ChavePixResponse chavePixToChavePixResponseDTO(ChavePix chavePix) {
         return new ChavePixResponse(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }

    @Override
    public List<ChavePixResponse> chavePixListToChavePixResponseDTOList(List<ChavePix> chavePixList) {
        return chavePixList.stream().map(this::chavePixToChavePixResponseDTO).collect(Collectors.toList());
    }
}
