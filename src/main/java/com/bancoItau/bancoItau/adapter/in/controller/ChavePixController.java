package com.bancoItau.bancoItau.adapter.in.controller;


import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.mapper.ChavePixMapper;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import com.bancoItau.bancoItau.domain.service.ChavePixService;
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



    @PostMapping()
    public ResponseEntity save(@RequestBody ChavePixRequestDTO chavePixRequestDTO) {
        ChavePixResponseDTO chavePixResponseDTO = chavePixService.save(chavePixRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(chavePixResponseDTO);
    }

    @GetMapping("{chavePixId}")
    public ResponseEntity<ChavePixResponseDTO> findById(@PathVariable UUID chavePixId) {
        ChavePix chavePix = chavePixService.findById(chavePixId);
        ChavePixResponseDTO chavePixResponseDTO = chavePixMapper.chavePixToChavePixResponseDTO(chavePix);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<ChavePixResponseDTO>> findAll() {
        List<ChavePix> chavePixList = chavePixService.findAll();
        List<ChavePixResponseDTO> chavePixResponseList = chavePixMapper.chavePixListToChavePixResponseDTOList(chavePixList);
        return ResponseEntity.status(HttpStatus.OK).body(chavePixResponseList);
    }

    @DeleteMapping("{chavePixId}")
    public void delete(@PathVariable UUID chavePixId) {
        chavePixService.delete(chavePixId);
    }


}
