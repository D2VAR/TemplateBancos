package com.itau.domain.service;

import com.itau.adapter.out.db.repository.ContaRepository;
import com.itau.domain.dto.ContaResponse;
import com.itau.domain.model.Conta;
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
        return contaRepository.findAll();
    }


    public Optional<Conta> getContabyId(UUID id) {
        return contaRepository.findById(id);
    }


    public Conta saveConta(ContaResponse contaResponse) {
        return contaRepository.save(Conta.builder()
                .agencia(contaResponse.getAgencia())
                .numeroConta(contaResponse.getNumeroConta())
                .saldo(contaResponse.getSaldo())
                .build());
    }

    public String deleteConta(UUID id) {
        contaRepository.deleteById(id);
        return null;
    }
}
