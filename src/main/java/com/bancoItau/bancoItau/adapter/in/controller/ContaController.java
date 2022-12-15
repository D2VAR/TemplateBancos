package com.bancoItau.bancoItau.adapter.in.controller;


import com.bancoItau.bancoItau.domain.dto.ContaResponse;
import com.bancoItau.bancoItau.domain.model.Conta;
import com.bancoItau.bancoItau.domain.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conta")
public class ContaController {


    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listAllContas() {
        return ResponseEntity.ok(contaService.listAllContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContabyId(@PathVariable UUID id) {
        return ResponseEntity.ok(contaService.getContabyId(id).get());
    }

    @PostMapping
    public ResponseEntity<Conta> saveConta(@RequestBody ContaResponse contaResponse) {
        return ResponseEntity.ok(contaService.saveConta(contaResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConta(@PathVariable UUID id) {
        return ResponseEntity.ok(contaService.deleteConta(id));
    }
}
