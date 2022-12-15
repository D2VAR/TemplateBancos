package com.itau.domain.service;

import com.itau.adapter.out.db.repository.ChavePixRepository;
import com.itau.adapter.out.db.repository.ContaRepository;
import com.itau.domain.dto.ChavePixRequest;
import com.itau.domain.dto.ChavePixResponse;
import com.itau.domain.model.ChavePix;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChavePixService{


    private final ChavePixRepository chavePixRepository;
    private final ContaService contaService;


    public ChavePixResponse save(ChavePixRequest chavePixRequest){
        var conta = contaService.getContabyId(chavePixRequest.getIdConta());
        ChavePix chavePix = new ChavePix(chavePixRequest.getValorChave(), chavePixRequest.getTipoChave(), conta);
        chavePixRepository.save(chavePix);
        return new ChavePixResponse(chavePix.getId(), chavePix.getValor(), chavePix.getConta());
    }

    public void delete(UUID chavePixId){
        ChavePix chavePix = findById(chavePixId);
        chavePixRepository.delete(chavePix);
    }

    public ChavePix findById(UUID chavePixId){
        return chavePixRepository.findById(chavePixId)
                .orElseThrow(() -> new RuntimeException("Chave Pix não encontrada!"));
    }

    public List<ChavePix> findAll(){
        return chavePixRepository.findAll();
    }


}
