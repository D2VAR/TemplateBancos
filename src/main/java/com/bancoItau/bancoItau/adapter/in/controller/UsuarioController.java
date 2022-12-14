package com.bancoItau.bancoItau.adapter.in.controller;


import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.dto.ContaResponseDTO;
import com.bancoItau.bancoItau.domain.dto.UsuarioResponseDTO;
import com.bancoItau.bancoItau.domain.model.Conta;
import com.bancoItau.bancoItau.domain.model.Usuario;
import com.bancoItau.bancoItau.domain.service.ContaService;
import com.bancoItau.bancoItau.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/busca-usuario")
    public List<Usuario> getAll() {

        return usuarioRepository.findAll();
    }

    @GetMapping("/busca-usuario/{id}")
    public Optional<Usuario> getById(@PathVariable UUID id) {
        return usuarioRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("atualizar/{id}")
    @Transactional
    @CacheEvict(value = "buscar-dados", allEntries = true)
    public ResponseEntity<UsuarioResponseDTO> atualizaUsuario(@PathVariable UUID id, @RequestBody UsuarioService usuarioService){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            Usuario usuario = usuarioService.atualizaUsuario(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(UUID id){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            usuarioRepository.deleteById(id);
        }
        else{
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}