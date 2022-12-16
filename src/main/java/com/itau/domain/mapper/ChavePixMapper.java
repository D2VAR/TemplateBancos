package com.itau.domain.mapper;


import com.itau.domain.dto.ChavePixMensagem;
import com.itau.domain.dto.ChavePixResponse;
import com.itau.domain.model.ChavePix;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component

public interface ChavePixMapper {

        ChavePixResponse toResponse(ChavePix chavePix);
        List<ChavePixResponse> toResponseList(List<ChavePix> chavePixList);

        ChavePix toModel(ChavePixMensagem chavePixMensagem);

        ChavePixMensagem toMensagem(ChavePix novaChavePix);
}
