package com.example.pixitau.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioRequest {

    private UUID id;

    private String cpf_cnpj;

    private String nome;

    private String telefone;

    private String email;

}
