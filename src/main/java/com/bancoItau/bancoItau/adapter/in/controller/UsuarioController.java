package com.bancoItau.bancoItau.adapter.in.controller;


import com.bancoItau.bancoItau.adapter.out.db.repository.UsuarioRepository;
import com.bancoItau.bancoItau.domain.dto.AtualizaUsuarioDTO;
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

    @GetMapping
    public ResponseEntity<List<Usuario>> listAllUsuarios() {

        return ResponseEntity.ok(usuarioService.listAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuariobyId(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioService.getUsuariobyId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioResponseDTO usuarioResponseDTO) {
        return ResponseEntity.ok(usuarioService.saveUsuario(usuarioResponseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateById(@PathVariable UUID id, @RequestBody AtualizaUsuarioDTO atualizaUsuarioDTO){
        return usuarioService.updateById(id, atualizaUsuarioDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePorId(@PathVariable UUID id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            usuarioService.deletePorId(id);
        } else {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
