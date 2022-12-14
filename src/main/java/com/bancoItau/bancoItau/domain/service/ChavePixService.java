package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.ChavePixRepository;
import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChavePixService {


    private ChavePixRepository chavePixRepository;
    public ChavePixResponseDTO salvar(ChavePixRequestDTO chavePixRequestDTO) {
        ChavePix chavePix = new ChavePix(chavePixRequestDTO);
        return new ChavePixResponseDTO(chavePixRepository.save(chavePix));
    }
}
