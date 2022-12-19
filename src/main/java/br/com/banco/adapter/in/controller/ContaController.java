package br.com.banco.adapter.in.controller;

import br.com.banco.domain.dto.ContaRequest;
import br.com.banco.domain.dto.ContaResponse;
import br.com.banco.domain.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponse> getContaById(@PathVariable UUID id) {
        return ResponseEntity.ok(contaService.getDadosConta(id));
    }

    @PostMapping
    public ResponseEntity<ContaResponse> saveConta(@RequestBody ContaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.saveConta(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {
        contaService.deleteConta(id);
        return ResponseEntity.noContent().build();
    }

}
