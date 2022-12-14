package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.domain.model.ChavePix;
import lombok.Data;

import java.util.UUID;

@Data
public class ChavePixResponseDTO {

    private UUID id;
    public ChavePixResponseDTO(ChavePix chavePix){
        this.id= chavePix.getId();
    }

}
