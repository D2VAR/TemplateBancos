package com.itau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.ChavePixRepository;
import com.bancoItau.bancoItau.domain.dto.ChavePixMensagem;
import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;
import com.bancoItau.bancoItau.domain.dto.ChavePixResponseDTO;
import com.bancoItau.bancoItau.domain.mapper.ChavePixMapper;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import com.bancoItau.bancoItau.port.in.CadastroChavePixInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChavePixService implements CadastroChavePixInputPort {


    private  final  ChavePixRepository chavePixRepository;
    ChavePixMapper mapper;

    public ChavePixResponseDTO save(ChavePixRequestDTO chavePixRequestDTO) {
        ChavePix chavePix = new ChavePix(chavePixRequestDTO);
        return new ChavePixResponseDTO(chavePixRepository.save(chavePix));
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


    @Override
    public void cadastrarChave(ChavePixMensagem chavePixMensagem) {
        ChavePix chavePix = mapper.toModel(chavePixMensagem);
        chavePixRepository.save(chavePix);
    }
}
