package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.dto.AtualizaUsuarioDTO;
import com.bancoItau.bancoItau.domain.dto.UsuarioResponseDTO;
import com.bancoItau.bancoItau.domain.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;
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
    public void deletePorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public ResponseEntity<UsuarioResponseDTO> updateById(@PathVariable UUID id, @RequestBody AtualizaUsuarioDTO atualizaUsuarioDTO){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            Usuario usuario = optional.get();
            if(atualizaUsuarioDTO.getNome() != null) {
                usuario.setNome(atualizaUsuarioDTO.getNome());
            }
            if(atualizaUsuarioDTO.getTelefone() == 0) {
                usuario.setTelefone(atualizaUsuarioDTO.getTelefone());
            }
            if(atualizaUsuarioDTO.getEmail() != null) {
                usuario.setEmail(atualizaUsuarioDTO.getEmail());
            }
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
        }
        return ResponseEntity.notFound().build();
    }

}
