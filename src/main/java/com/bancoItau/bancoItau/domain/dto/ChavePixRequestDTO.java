package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChavePixRequestDTO {
    private UUID id;

    private String valor;
    private Conta conta;


}
