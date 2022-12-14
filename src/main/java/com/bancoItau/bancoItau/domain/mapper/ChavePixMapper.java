package com.bancoItau.bancoItau.domain.mapper;


import com.bancoItau.bancoItau.domain.dto.ChavePixMensagem;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component

public interface ChavePixMapper {

    ChavePixResponseDTO toResponse(ChavePix chavePix);
    List<ChavePixResponseDTO> toResponseList(List<ChavePix> chavePixList);

    ChavePix toModel(ChavePixMensagem chavePixMensagem);

}
