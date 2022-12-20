package br.com.banco.service;

import br.com.banco.adapter.out.api.bacen.ApiBacen;
import br.com.banco.adapter.out.api.bacen.dto.ApiBacenResponse;
import br.com.banco.adapter.out.db.repository.ChavePixRepository;
import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.dto.chavepix.ChavePixRequest;
import br.com.banco.domain.dto.chavepix.ChavePixResponse;
import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.exceptions.ChavePixAlreadyExistException;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.model.ChavePix;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.ChavePixService;
import br.com.banco.domain.service.ContaService;
import br.com.banco.port.out.CadastroChavePixOutput;
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
public class ChavePixServiceTest {

    @InjectMocks
    private ChavePixService service;

    @Mock
    private ChavePixRepository repository;

    @Mock
    private CadastroChavePixOutput cadastroChavePixOutput;

    @Mock
    private ApiBacen apiBacen;

    @Mock
    private ContaService contaService;


    private Conta buildConta() {
        return Conta.builder()
                .id(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
                .agencia("125165")
                .numero("1215456")
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

    private ChavePix buildChavePix() {
        return ChavePix.builder()
                .id(UUID.fromString("315fb189-12b5-47ad-980b-ea749cc21539"))
                .valor("123456789")
                .tipo(TipoChave.CPF_CNPJ)
                .conta(buildConta())
                .build();
    }

    public ChavePixRequest buildChavePixRequest() {
        return ChavePixRequest.builder()
                .idConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
                .valorChave("123456789")
                .tipoChave(TipoChave.CPF_CNPJ)
                .build();
    }

    public ChavePixMensagem buildChavePixMensagem() {
        return ChavePixMensagem.builder()
                .cpfCnpj("123456789")
                .tipoChave(TipoChave.CPF_CNPJ)
                .agenciaConta("125165")
                .valorChave("123456789")
                .codBanco("123")
                .numeroConta("1215456")
                .build();
    }

    public ApiBacenResponse buildApiBacen() {
        return ApiBacenResponse.builder()
                .codBanco("123")
                .chaveExists(true)
                .tipoChave(TipoChave.CPF_CNPJ)
                .valorChave("123456789")
                .build();
    }

    public ApiBacenResponse buildApiBacenFalse() {
        return ApiBacenResponse.builder()
                .codBanco("123")
                .chaveExists(false)
                .tipoChave(TipoChave.CPF_CNPJ)
                .valorChave("123456789")
                .build();
    }


    @Test
    public void deleteChavePixTest() {
        when(repository.findById(UUID.fromString("315fb189-12b5-47ad-980b-ea749cc21539")))
                .thenReturn(java.util.Optional.of(buildChavePix()));
        service.delete(UUID.fromString("315fb189-12b5-47ad-980b-ea749cc21539"));

        verify(repository, times(1))
                .delete(any());
    }

    @Test
    public void saveChavePixTest() {
        when(repository.save(any())).thenReturn(buildChavePix());
        ChavePixResponse result = service.save(buildChavePix());
        verify(repository, times(1)).save(any(ChavePix.class));
        assertEquals("123456789", result.getValor());
    }

    @Test
    public void cadastrarChavePixInternaTest() {
        when(repository.save(any())).thenReturn(buildChavePix());
        service.cadastrarChaveInterna(buildChavePixMensagem());
        verify(repository, times(1)).save(any(ChavePix.class));

    }

    @Test
    public void cadastrarChaveBacenTest() {
        when(contaService.getById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .thenReturn(buildConta());
        when(apiBacen.chavePixExists("123456789")).thenReturn(buildApiBacenFalse());
        service.cadastrarChaveBacen(buildChavePixRequest());
        verify(cadastroChavePixOutput, times(1)).enviarMensagemCadastroChave(any());

    }

    @Test
    public void validarExistenciaChaveBacenTest() {
        when(apiBacen.chavePixExists("123456789")).thenReturn(buildApiBacen());
        service.validarExistenciaChavePixBacen(buildApiBacen().getValorChave());
        verify(apiBacen, times(1)).chavePixExists(anyString());
        assertEquals(true, buildApiBacen().isChaveExists());
    }

    @Test
    public void validarExistenciaChaveBacenExcepitionTest() {
        when(apiBacen.chavePixExists("123456789")).thenReturn(buildApiBacenFalse());
        ChavePixNotFoundException ex = Assertions.assertThrows(ChavePixNotFoundException.class, () ->
                service.validarExistenciaChavePixBacen(buildApiBacen().getValorChave()));
        assertEquals("Chave Pix não encontrada!", ex.getMessage());
    }

    @Test
    public void validarNaoExistenciaChavePixBacenTest() {
        when(apiBacen.chavePixExists("123456789")).thenReturn(buildApiBacenFalse());
        service.validarNaoExistenciaChavePixBacen("123456789");
        verify(apiBacen, times(1)).chavePixExists(anyString());
        assertEquals(true, buildApiBacen().isChaveExists());
    }

    @Test
    public void validarNaoExistenciaChavePixBacenExcepitionTest() {
        when(apiBacen.chavePixExists("123456789")).thenReturn(buildApiBacen());
        ChavePixAlreadyExistException ex = Assertions.assertThrows(ChavePixAlreadyExistException.class, () ->
                service.validarNaoExistenciaChavePixBacen(buildApiBacen().getValorChave()));
        assertEquals("Chave Pix já existente!", ex.getMessage());
    }

    @Test
    public void getByValorTest() {
        when(repository.findByValor("123456789")).thenReturn(Optional.ofNullable(buildChavePix()));
        ChavePixResponse result = service.getByValor("123456789");
        verify(repository, times(1)).findByValor(anyString());
        assertEquals("123456789", result.getValor());
    }

    @Test
    public void getByValorExcepitionTest() {
        when(repository.findByValor("123456789")).thenReturn(Optional.empty());
        ChavePixNotFoundException ex = Assertions.assertThrows(ChavePixNotFoundException.class, () ->
                service.getByValor("123456789"));
        assertEquals("Chave Pix não encontrada!", ex.getMessage());
    }

}