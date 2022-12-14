package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.dto.UsuarioResponseDTO;
import com.bancoItau.bancoItau.domain.model.Usuario;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService  {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> listAllUsuarios() {
        return usuarioRepository.findAll();
    }


    public Usuario getUsuariobyId(UUID id) {
        return usuarioRepository.findById(id).get();
    }


    public Usuario saveUsuario(UsuarioResponseDTO UsuarioResponseDTO) {
        return usuarioRepository.save(Usuario.builder()
                .nome(UsuarioResponseDTO.getNome())
                .cpf(UsuarioResponseDTO.getCpf())
                .telefone(UsuarioResponseDTO.getTelefone())
                .email(UsuarioResponseDTO.getEmail())
                .build());


    }

}
