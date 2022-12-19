package br.com.banco.domain.mapper;


import br.com.banco.domain.dto.chave.ChavePixMensagem;
import br.com.banco.domain.dto.chave.ChavePixResponse;
import br.com.banco.domain.model.ChavePix;
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
