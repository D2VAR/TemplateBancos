package com.bancoItau.bancoItau.adapter.in.controller;


import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.dto.UsuarioResponseDTO;
import com.bancoItau.bancoItau.domain.model.Usuario;
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

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/busca-usuario")
    public ResponseEntity<List<Usuario>> listAllUsuarios() {

        return ResponseEntity.ok(usuarioService.listAllUsuarios());
    }

    @GetMapping("/busca-usuario/{id}")
    public ResponseEntity<Usuario> getUsuariobyId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.getUsuariobyId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioResponseDTO usuarioResponseDTO) {
        return ResponseEntity.ok(usuarioService.saveUsuario(usuarioResponseDTO));
    }

   /* @PutMapping("atualizar/{id}")
    @Transactional
    @CacheEvict(value = "buscar-dados", allEntries = true)
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable UUID id, @RequestBody AtualizaUsuario atualizaUsuario){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            Usuario usuario = AtualizaUsuario.atualizar(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
        }
        return ResponseEntity.notFound().build();
    }*/

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
