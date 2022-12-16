package com.itau.domain.dto;

import com.itau.domain.model.Conta;
import lombok.AllArgsConstructor;
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
