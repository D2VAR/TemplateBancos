package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.domain.model.ChavePix;
import com.bancoItau.bancoItau.domain.model.Conta;
import lombok.Data;

import java.util.UUID;

@Data
public class ChavePixResponseDTO {

    private UUID id;
    private String valor;
    public ChavePixResponseDTO(ChavePix chavePix){
        this.id= chavePix.getId();
    }

    public ChavePix get() {
        return get();
    }
    private Conta conta;

}
