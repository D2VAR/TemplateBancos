package com.bancoItau.bancoItau.adapter.in.controller;

import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.model.Usuario;
import lombok.Data;

import java.util.UUID;

@Data
public class AtualizaUsuario {

    private String nome;
    private int telefone;
    private String email;


    public Usuario atualizar(UUID id, UsuarioRepository usuarioRepository) {

        Usuario usuario = usuarioRepository.getReferenceById(id);

        usuario.setNome(this.nome);
        usuario.setTelefone(this.telefone);
        usuario.setEmail(this.email);

        return usuario;
    }
}