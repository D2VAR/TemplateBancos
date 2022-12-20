package br.com.banco.adapter.in.controller;

import br.com.banco.domain.dto.usuario.UsuarioRequest;
import br.com.banco.domain.dto.usuario.UsuarioResponse;
import br.com.banco.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController{
    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuariobyId(@PathVariable UUID id){
        return ResponseEntity.ok(usuarioService.getUsuario(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> saveUsuario(@RequestBody UsuarioRequest requestBody){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(requestBody));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateById(@PathVariable UUID id, @RequestBody UsuarioRequest requestBody){
        usuarioService.updateById(id, requestBody);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePorId(@PathVariable UUID id){
        usuarioService.deletePorId(id);
        return ResponseEntity.noContent().build();
    }
}
