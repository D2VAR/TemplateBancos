package br.com.banco.controller;

import br.com.banco.domain.dto.TransacaoPixRequest;
import br.com.banco.domain.dto.TransacaoPixResponse;
import br.com.banco.domain.service.TransacaoPixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoPixController {

    private final TransacaoPixService transacaoPixService;

    @PostMapping
    public ResponseEntity<TransacaoPixResponse> envioPix(@RequestBody TransacaoPixRequest transacaoPixRequest) {
        TransacaoPixResponse transacaoPixResponse = transacaoPixService.enviaPix(transacaoPixRequest);
        return ResponseEntity.ok(transacaoPixResponse);
    }
}
