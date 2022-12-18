package br.com.banco.adapter.in.controller;

import br.com.banco.domain.dto.ContaResponse;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> listAllContas() {
        return ResponseEntity.ok(contaService.listAllContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(@PathVariable UUID id) {
        return ResponseEntity.ok(contaService.getContaById(id));
    }

    @PostMapping
    public ResponseEntity<Conta> saveConta(@RequestBody ContaResponse contaResponse) {
        return ResponseEntity.ok(contaService.saveConta(contaResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable UUID id) {
        contaService.deleteConta(id);
        return ResponseEntity.noContent().build();
    }
}
