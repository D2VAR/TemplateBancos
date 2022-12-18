package br.com.banco.adapter.in.controller;

import br.com.banco.domain.dto.AtualizaUsuarioDTO;
import br.com.banco.domain.dto.UsuarioResponseDTO;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listAllUsuarios() {

        return ResponseEntity.ok(usuarioService.listAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuariobyId(@PathVariable UUID id) {
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
    public ResponseEntity<Void> deletePorId(@PathVariable UUID id) {
        usuarioService.deletePorId(id);
        return ResponseEntity.noContent().build();
    }
}
