package br.com.banco.service;

import br.com.banco.adapter.out.db.repository.ContaRepository;
import br.com.banco.domain.dto.conta.ContaRequest;
import br.com.banco.domain.dto.conta.ContaResponse;
import br.com.banco.domain.exceptions.ContaNotFoundException;
import br.com.banco.domain.exceptions.SaldoInsuficienteException;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.ContaService;
import br.com.banco.domain.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ContaServiceTest {
    @Mock
    private ContaRepository repository;
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private ContaService service;


    private Conta buildConta() {
        return Conta.builder()
                .id(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
                .agencia("125165")
                .numero("1215456")
                .saldo(BigDecimal.valueOf(100))
                .usuario(buildUsuario())
                .build();
    }

    private Usuario buildUsuario() {
        return Usuario.builder()
                .id(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .nome("Joao")
                .cpf("123456789")
                .build();
    }

    private ContaRequest buildContaRequest() {
        return ContaRequest.builder()
                .idUsuario(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .agencia("125165")
                .build();
    }


    @Test
    public void getByIdTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        Conta result = service.getById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));

        verify(repository, times(1))
                .findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());

    }

    @Test
    public void getByIdExceptionTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.empty());
        ContaNotFoundException ex = Assertions.assertThrows(ContaNotFoundException.class, () ->
                service.getById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
        );
        assertEquals("Conta não encontrada!", ex.getMessage());

    }

    @Test
    public void getContaResponseByIdTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        ContaResponse result = service.getContaResponseById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));

        verify(repository, times(1))
                .findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());
    }

    @Test
    public void deleteContaTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        service.deleteConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        verify(repository, times(1))
                .deleteById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
    }

    @Test
    public void getDadosContaTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        ContaResponse result = service.getContaResponseById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        verify(repository, times(1))
                .findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());
    }

    @Test
    public void saveContaTest() {

        when(usuarioService.getUsuariobyId(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(buildUsuario());
        when(repository.save(any(Conta.class))).thenReturn(buildConta());
        ContaResponse result = service.saveConta(buildContaRequest());
        verify(repository, times(1)).save(any(Conta.class));
        assertEquals("125165", result.getAgencia());
    }

    @Test
    public void getContaByAgenciaAndNumeroTest() {
        when(repository.findByAgenciaAndNumero("125165", "1215456"))
                .thenReturn(Optional.of(buildConta()));
        Conta result = service.getByAgenciaAndNumero("125165", "1215456");
        verify(repository, times(1))
                .findByAgenciaAndNumero("125165", "1215456");
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());
    }

    @Test
    public void updateSaldoTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        when(repository.save(any(Conta.class))).thenReturn(buildConta());
        service.updateSaldo(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), BigDecimal.valueOf(100));
        verify(repository, times(1)).save(any(Conta.class));
        assertEquals(BigDecimal.valueOf(100), buildConta().getSaldo());
    }

    @Test
    public void debitarContaTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        when(repository.save(any(Conta.class))).thenReturn(buildConta());
        service.debitarConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), BigDecimal.valueOf(100));
        verify(repository, times(1)).save(any(Conta.class));
        assertEquals(BigDecimal.valueOf(100), buildConta().getSaldo());
    }

    @Test
    public void debitarContaExceptionTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        SaldoInsuficienteException ex = Assertions.assertThrows(SaldoInsuficienteException.class, () ->
                service.debitarConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), BigDecimal.valueOf(1000))
        );
        assertEquals("Saldo insuficiente!", ex.getMessage());
    }

    @Test
    public void creditarContaTest() {
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildConta()));
        when(repository.save(any(Conta.class))).thenReturn(buildConta());
        service.creditarConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), BigDecimal.valueOf(100));
        verify(repository, times(1)).save(any(Conta.class));
        assertEquals(BigDecimal.valueOf(100), buildConta().getSaldo());
    }
}
