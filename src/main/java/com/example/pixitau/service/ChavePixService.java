package com.example.pixitau.service;

import com.example.pixitau.dto.ChavePixRequest;
import com.example.pixitau.dto.ChavePixResponse;
import com.example.pixitau.model.ChavePix;
import com.example.pixitau.repository.ChavePixRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChavePixService {


    private ChavePixRepository chavePixRepository;
    public ChavePixResponse salvar(ChavePixRequest chavePixRequest) {
        ChavePix chavePix = new ChavePix(chavePixRequest);
        return new ChavePixResponse(chavePixRepository.save(chavePix));
    }
}
