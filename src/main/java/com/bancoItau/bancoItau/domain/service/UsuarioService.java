package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.model.Usuario;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class UsuarioService  {

    private String nome;
    private String email;
    private int telefone;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioService usuarioService) {
        this.usuarioRepository = usuarioService.usuarioRepository;
    }


    public Usuario atualizaUsuario(UUID id, UsuarioRepository usuarioRepository) {

        Usuario usuario = this.usuarioRepository.getReferenceById(id);

        usuario.setNome(this.nome);
        usuario.setTelefone(this.telefone);
        usuario.setEmail(this.email);

        return usuario;
    }



}
