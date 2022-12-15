package com.bancoItau.bancoItau.domain.service;

import com.bancoItau.bancoItau.adapter.out.db.repository.ContaRepository;
import com.bancoItau.bancoItau.domain.dto.ContaResponse;
import com.bancoItau.bancoItau.domain.model.Conta;
import org.springframework.stereotype.Service;


import java.util.List;
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


    public Conta getContabyId(UUID id) {
        return contaRepository.findById(id).get();
    }


    public Conta saveConta(ContaResponse contaResponse) {
        return contaRepository.save(Conta.builder()
                .agencia(contaResponse.getAgencia())
                .nConta(contaResponse.getConta())
                .saldo(contaResponse.getSaldo())
                .build());
    }

    public String deleteConta(UUID id) {
        contaRepository.deleteById(id);
        return null;
    }
}
