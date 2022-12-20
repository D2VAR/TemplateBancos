package br.com.banco.service;

import br.com.banco.adapter.out.db.repository.ContaRepository;
import br.com.banco.domain.dto.conta.ContaRequest;
import br.com.banco.domain.dto.conta.ContaResponse;
import br.com.banco.domain.exceptions.ContaNotFoundException;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ContaServiceTest{
    @Mock
    private ContaRepository repository;
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private ContaService service;


    private Conta buildContaResponse(){
        return Conta.builder()
                .id(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
                .agencia("125165")
                .numero("1215456")
                .usuario(buildUsuario())
                .build();
    }

    private Usuario buildUsuario(){
        return Usuario.builder()
                .id(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .nome("Joao")
                .cpf("123456789")
                .build();
    }

    private ContaRequest buildContaRequest(){
        return ContaRequest.builder()
                .idUsuario(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .agencia("125165")
                .build();
    }


    @Test
    void getContaByIdTest(){
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildContaResponse()));
        Conta result = service.getById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));

        verify(repository, times(1))
                .findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());

    }

    @Test
    void getByIdExceptionTest(){
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.empty());
        ContaNotFoundException ex = Assertions.assertThrows(ContaNotFoundException.class, () ->
                service.getById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
        );
        assertEquals("Conta não encontrada!", ex.getMessage());

    }

    @Test
    void deleteContaTest(){
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildContaResponse()));
        service.deleteConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        verify(repository, times(1))
                .deleteById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
    }

    @Test
    void getDadosContaTest(){
        when(repository.findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(Optional.of(buildContaResponse()));
        ContaResponse result = service.getContaResponseById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        verify(repository, times(1))
                .findById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"));
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());
    }

    @Test
    void saveContaTest(){

        when(usuarioService.getUsuariobyId(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(buildUsuario());
        when(repository.save(any(Conta.class))).thenReturn(buildContaResponse());
        ContaResponse result = service.saveConta(buildContaRequest());
        verify(repository, times(1)).save(any(Conta.class));
        assertEquals("125165", result.getAgencia());
    }

    @Test
    void getContaByAgenciaAndNumeroTest(){
        when(repository.findByAgenciaAndNumero("125165", "1215456"))
                .thenReturn(Optional.of(buildContaResponse()));
        Conta result = service.getByAgenciaAndNumero("125165", "1215456");
        verify(repository, times(1))
                .findByAgenciaAndNumero("125165", "1215456");
        assertEquals(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"), result.getId());
    }
}
