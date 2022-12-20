package br.com.banco.domain.service;

import br.com.banco.adapter.out.db.repository.ContaRepository;
import br.com.banco.domain.dto.ContaRequest;
import br.com.banco.domain.dto.ContaResponse;
import br.com.banco.domain.dto.RecebedorPixMensagem;
import br.com.banco.domain.exceptions.ContaNotFoundException;
import br.com.banco.domain.exceptions.SaldoInsuficienteException;
import br.com.banco.domain.model.Conta;
import br.com.banco.port.in.RecebedorPixInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService implements RecebedorPixInputPort{
    private final ContaRepository contaRepository;
    private final UsuarioService usuarioService;

    public void deleteConta(UUID id){
        getById(id);
        contaRepository.deleteById(id);
    }

    public ContaResponse getContaResponseById(UUID id){
        var conta = getById(id);
        return new ContaResponse(conta);
    }

    public Conta getById(UUID id){
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada!"));
    }

    public ContaResponse saveConta(ContaRequest request){
        var conta = gerarNovaConta(request);
        contaRepository.save(conta);
        return new ContaResponse(conta);
    }

    private Conta gerarNovaConta(ContaRequest request){
        var usuario = usuarioService.getUsuariobyId(request.getIdUsuario());
        var agencia = request.getAgencia();
        var numero = gerarNumeroConta(agencia);
        return new Conta(agencia, numero, usuario);
    }

    private String gerarNumeroConta(String agencia){
        var count = contaRepository.countByAgencia(agencia);
        return String.format("%07d", count + 1);

    }

    public Conta getByAgenciaAndNumero(String agencia, String numero){
        return contaRepository.findByAgenciaAndNumero(agencia, numero)
                .orElseThrow(() -> new ContaNotFoundException("Conta nao encontrada!"));
    }


    public void updateSaldo(UUID id, BigDecimal saldo){
        var conta = getById(id);
        conta.setSaldo(saldo);
        contaRepository.save(conta);
    }

    public void debitarConta(UUID id, BigDecimal valor){
        var conta = getById(id);
        if (conta.getSaldo().compareTo(valor) >= 0){
            BigDecimal saldo = conta.getSaldo().subtract(valor);
            updateSaldo(id, saldo);

        } else
            contaRepository.findById(id)
                    .orElseThrow(() -> new SaldoInsuficienteException("Saldo insuficiente!"));

    }

    public void creditarConta(UUID id, BigDecimal valor){
        Conta conta = getById(id);
        BigDecimal saldo = conta.getSaldo().add(valor);
        updateSaldo(id, saldo);
    }


    @Override
    public void validarAlteracaoSaldo(RecebedorPixMensagem recebedorPixMensagem){
        if (recebedorPixMensagem.getPixRealizado())
            creditarConta(UUID.fromString(recebedorPixMensagem.getTransactionId()), new BigDecimal(recebedorPixMensagem.getValorTransferencia()));
//        debitarConta(UUID.fromString(recebedorPixMensagem.getTransactionId()), new BigDecimal(recebedorPixMensagem.getValorTransferencia()));
    }

}
