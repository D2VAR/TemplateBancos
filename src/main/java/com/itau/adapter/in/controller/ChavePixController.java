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
@RequestMapping ("/api/chavepix")
@RequiredArgsConstructor
public class ChavePixController {
    private final ChavePixService chavePixService;
    private final ChavePixMapper chavePixMapper;

    @PostMapping
    public ResponseEntity<ChavePixResponse> save(@RequestBody ChavePixRequest chavePixRequest) {
        ChavePixResponse chavePixResponse = chavePixService.save(chavePixRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(chavePixResponse);
    }

    @GetMapping("{chavePixId}")
    public ResponseEntity<ChavePixResponse> findById(@PathVariable UUID chavePixId) {
        ChavePix chavePix = chavePixService.findById(chavePixId);
        ChavePixResponse chavePixResponse = chavePixMapper.chavePixToChavePixResponseDTO(chavePix);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponse);
    }

    @GetMapping
    public ResponseEntity<List<ChavePixResponse>> findAll() {
        List<ChavePix> chavePixList = chavePixService.findAll();
        List<ChavePixResponse> chavePixResponseList = chavePixMapper.chavePixListToChavePixResponseDTOList(chavePixList);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponseList);
    }

    @DeleteMapping("{chavePixId}")
    public void delete(@PathVariable UUID chavePixId) {
        chavePixService.delete(chavePixId);
    }


}
