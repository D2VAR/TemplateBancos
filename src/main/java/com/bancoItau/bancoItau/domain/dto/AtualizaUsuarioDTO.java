package com.bancoItau.bancoItau.domain.dto;

import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.model.Usuario;
import lombok.Data;

import java.util.UUID;

@Data
public class AtualizaUsuarioDTO {

    private String nome;
    private int telefone;
    private String email;

}