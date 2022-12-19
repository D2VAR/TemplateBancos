package br.com.banco.adapter.in.controller;

import br.com.banco.domain.dto.chave.ChavePixRequest;
import br.com.banco.domain.service.ChavePixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chave-pix")
@RequiredArgsConstructor
public class ChavePixController{
    private final ChavePixService chavePixService;

    @PostMapping
    public ResponseEntity<Void> cadastrarChavePix(@RequestBody ChavePixRequest chavePixRequest){
        chavePixService.cadastrarChaveBacen(chavePixRequest);
        return ResponseEntity.noContent().build();
    }

}
