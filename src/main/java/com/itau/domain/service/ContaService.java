package com.itau.domain.service;

import com.itau.adapter.out.db.repository.ContaRepository;
import com.itau.domain.dto.ContaResponse;
import com.itau.domain.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService{


    private final ContaRepository contaRepository;
    private final UsuarioService usuarioService;


    public Conta getContaByAgenciaAndNumeroConta(String agencia, String numeroConta){
        return contaRepository.findByAgenciaAndNumeroConta(agencia, numeroConta)
                .orElseThrow(() -> new RuntimeException("Conta nao encontrada!"));
    }

    public List<Conta> listAllContas(){
        return contaRepository.findAll();
    }


    public Conta getContaById(UUID id){
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta nao encontrada!"));
    }


    public Conta saveConta(ContaResponse contaResponse){
        var usuario = usuarioService.getUsuariobyId(contaResponse.getIdUsuario());
        return contaRepository.save(Conta.builder()
                .agencia(contaResponse.getAgencia())
                .numeroConta(contaResponse.getNumeroConta())
                .saldo(contaResponse.getSaldo())
                .usuario(usuario)
                .build());
    }

    public void deleteConta(UUID id){
        getContaById(id);
        contaRepository.deleteById(id);
    }
}
