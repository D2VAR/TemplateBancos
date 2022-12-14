package com.bancoItau.bancoItau.adapter.in.controller;



import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.service.ChavePixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/chavepix")
@RequiredArgsConstructor
public class ChavePixController {

    private final ChavePixService chavePixService;

    @PostMapping

    public ResponseEntity <ChavePixResponseDTO>  cadastrar (@RequestBody ChavePixRequestDTO chavePixRequestDTO){
        ChavePixResponseDTO chavePixResponse = chavePixService.salvar(chavePixRequestDTO);
        return ResponseEntity.ok().body(chavePixResponse);
    }


}
