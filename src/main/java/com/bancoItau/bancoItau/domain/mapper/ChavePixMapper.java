package com.bancoItau.bancoItau.domain.mapper;


import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component

public interface ChavePixMapper {

    ChavePixResponseDTO chavePixToChavePixResponseDTO(ChavePix chavePix);

    List<ChavePixResponseDTO> chavePixListToChavePixResponseDTOList(List<ChavePix> chavePixList);



}
