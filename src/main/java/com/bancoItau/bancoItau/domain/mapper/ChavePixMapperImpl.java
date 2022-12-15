package com.bancoItau.bancoItau.domain.mapper;

import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChavePixMapperImpl implements ChavePixMapper{

    @Override
    public ChavePixResponseDTO chavePixToChavePixResponseDTO(ChavePix chavePix) {
         return new ChavePixResponseDTO(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }


    @Override
    public List<ChavePixResponseDTO> chavePixListToChavePixResponseDTOList(List<ChavePix> chavePixList) {
        return null;
    }


}
