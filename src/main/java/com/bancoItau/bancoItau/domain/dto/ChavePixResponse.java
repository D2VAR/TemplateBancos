package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class ChavePixResponse{
    private UUID id;
    private String valor;
    private Conta conta;

}
