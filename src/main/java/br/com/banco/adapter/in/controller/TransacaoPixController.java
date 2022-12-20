package br.com.banco.adapter.in.controller;

import br.com.banco.domain.dto.transacaopix.TransacaoPixRequest;
import br.com.banco.domain.service.TransacaoPixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class TransacaoPixController{
    private final TransacaoPixService transacaoPixService;

    @PostMapping
    public ResponseEntity<Void> envioPix(@RequestBody TransacaoPixRequest transacaoPixRequest){
        transacaoPixService.enviarPix(transacaoPixRequest);
        return ResponseEntity.noContent().build();
    }
}
