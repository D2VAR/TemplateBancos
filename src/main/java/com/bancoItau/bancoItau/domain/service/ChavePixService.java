package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.ChavePixRepository;
import com.bancoItau.bancoItau.domain.dto.ChavePixRequest;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponse;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChavePixService {


    private  final  ChavePixRepository chavePixRepository;



    public ChavePixResponse save(ChavePixRequest chavePixRequest) {
        ChavePix chavePix = ChavePix.builder()
                .id(UUID.randomUUID())
                .valor(chavePixRequest.getValorChave())
                .build();
        chavePixRepository.save(chavePix);
        return new ChavePixResponse(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }


    public void delete(UUID chavePixId) {
        ChavePix chavePix = findById(chavePixId);
        chavePixRepository.delete(chavePix);
    }

    public ChavePix findById(UUID chavePixId) {
        return chavePixRepository.findById(chavePixId)
                .orElseThrow(() -> new RuntimeException("Chave Pix não encontrada!"));
    }

    public List<ChavePix> findAll() {
        return chavePixRepository.findAll();
    }



}
