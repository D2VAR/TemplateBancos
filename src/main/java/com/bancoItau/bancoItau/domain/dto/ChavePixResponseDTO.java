package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.domain.model.ChavePix;
import com.bancoItau.bancoItau.domain.model.Conta;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter

public class ChavePixResponseDTO {

    private UUID id;
    private String valor;
    private Conta conta;


    public ChavePixResponseDTO(UUID id, String valor, Conta conta) {
        this.id = id;
        this.valor = valor;
        this.conta = conta;
    }

}
