package com.itau.adapter.in.controller;

import com.itau.domain.dto.ChavePixRequest;
import com.itau.domain.dto.ChavePixResponse;
import com.itau.domain.mapper.ChavePixMapper;
import com.itau.domain.model.ChavePix;
import com.itau.domain.service.ChavePixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/chave-pix")
@RequiredArgsConstructor
public class ChavePixController {
    private final ChavePixService chavePixService;
    private final ChavePixMapper chavePixMapper;

    @PostMapping
    public ResponseEntity<Void> cadastrarChavePix(@RequestBody ChavePixRequest chavePixRequest) {
        chavePixService.cadastrarChaveBacen(chavePixRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{valorChavePix}")
    public ResponseEntity<ChavePixResponse> findChavePixByValor(@PathVariable String valorChavePix) {
        ChavePix chavePix = chavePixService.findByValor(valorChavePix);
        ChavePixResponse chavePixResponse = chavePixMapper.toResponse(chavePix);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponse);
    }

    @GetMapping
    public ResponseEntity<List<ChavePixResponse>> findAll() {
        List<ChavePix> chavePixList = chavePixService.findAll();
        List<ChavePixResponse> chavePixResponseList = chavePixMapper.toResponseList(chavePixList);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponseList);
    }

    @DeleteMapping("{chavePixId}")
    public ResponseEntity<Void> delete(@PathVariable UUID chavePixId) {
        chavePixService.delete(chavePixId);
        return ResponseEntity.noContent().build();
    }
}
