package br.com.banco.domain.service;

import br.com.banco.adapter.out.db.repository.ContaRepository;
import br.com.banco.domain.dto.ContaRequest;
import br.com.banco.domain.dto.ContaResponse;
import br.com.banco.domain.exceptions.ContaNotFoundException;
import br.com.banco.domain.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;
    private final UsuarioService usuarioService;


    public void deleteConta(UUID id) {
        getContaById(id);
        contaRepository.deleteById(id);
    }

    public Conta getContaById(UUID id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta nao encontrada!"));
    }

    public ContaResponse getDadosConta(UUID id) {
        var conta = getContaById(id);
        return buildContaResponse(conta);
    }

    private ContaResponse buildContaResponse(Conta conta) {
        return ContaResponse.builder()
                .id(conta.getId())
                .agencia(conta.getAgencia())
                .numero(conta.getNumero())
                .idUsuario(conta.getUsuario().getId())
                .build();
    }


    public ContaResponse saveConta(ContaRequest request) {
        var conta = gerarNovaConta(request);
        contaRepository.save(conta);
        return buildContaResponse(conta);
    }

    private Conta gerarNovaConta(ContaRequest request) {
        var usuario = usuarioService.getUsuariobyId(request.getIdUsuario());
        var agencia = request.getAgencia();
        var numero = gerarNumeroConta(agencia);
        return new Conta(agencia, numero, usuario);
    }

    private String gerarNumeroConta(String agencia) {
        var count = contaRepository.countByAgencia(agencia);
        return String.format("%07d", count + 1);

    }

    public Conta getContaByAgenciaAndNumero(String agencia, String numero) {
        return contaRepository.findByAgenciaAndNumero(agencia, numero)
                .orElseThrow(() -> new ContaNotFoundException("Conta nao encontrada!"));
    }
}
