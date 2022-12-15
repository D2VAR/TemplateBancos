package com.itau.domain.mapper;


import com.itau.domain.dto.ChavePixResponse;
import com.itau.domain.model.ChavePix;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component

public interface ChavePixMapper {

    ChavePixResponse chavePixToChavePixResponseDTO(ChavePix chavePix);

    List<ChavePixResponse> chavePixListToChavePixResponseDTOList(List<ChavePix> chavePixList);



}
