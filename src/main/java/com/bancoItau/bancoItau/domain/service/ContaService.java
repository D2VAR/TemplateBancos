package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.ContaRepository;
import com.bancoItau.bancoItau.domain.dto.ContaResponseDTO;
import com.bancoItau.bancoItau.domain.model.Conta;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService  {


    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }


    public List<Conta> listAllContas() {
        return (List<Conta>) contaRepository.findAll();
    }


    public Optional<Conta> getContabyId(UUID id) {
        return contaRepository.findById(id);
    }


    public Conta saveConta(ContaResponseDTO contaResponseDTO) {
        return contaRepository.save(Conta.builder()
                .agencia(contaResponseDTO.getAgencia())
                .nConta(contaResponseDTO.getNconta())
                .saldo(contaResponseDTO.getSaldo())
                .build());

    }

    public String deleteConta(UUID id) {
        contaRepository.deleteById(id);
        return null;
    }
}
