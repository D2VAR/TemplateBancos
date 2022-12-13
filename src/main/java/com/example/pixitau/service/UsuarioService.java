package com.example.pixitau.service;

import com.example.pixitau.dto.UsuarioRequest;
import com.example.pixitau.mapper.UsuarioMapper;
import com.example.pixitau.model.Usuario;
import com.example.pixitau.repository.UsuarioRepository;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    private UsuarioMapper usuarioMapper;

    public Usuario save(UsuarioRequest usuarioRequest) {
      Usuario usuario = usuarioMapper.toModel(usuarioRequest);
       usuarioRepository.save(usuario);
        return usuario;
    }

    public List<Usuario> findAll() {

        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario findById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid id"));
    }

    public Usuario update(UUID uuid, UsuarioRequest usuarioRequest) {
       Usuario  usuario= findById(uuid);
        usuario = usuarioMapper.toModel(usuarioRequest);
       usuarioRepository.save(usuario);
        return usuario;
    }

//    public void delete(UUID id) {
//       Usuario usuario = findById(id);
//       UsuarioRepository.delete(usuario);
//    }

}
