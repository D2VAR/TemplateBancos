package com.bancoItau.bancoItau.domain.mapper;


import com.bancoItau.bancoItau.domain.dto.ChavePixResponse;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component

public interface ChavePixMapper {

    ChavePixResponse chavePixToChavePixResponseDTO(ChavePix chavePix);

    List<ChavePixResponse> chavePixListToChavePixResponseDTOList(List<ChavePix> chavePixList);



}
