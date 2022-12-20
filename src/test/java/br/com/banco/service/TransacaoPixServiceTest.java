package br.com.banco.service;

import br.com.banco.adapter.out.api.bacen.dto.ApiBacenResponse;
import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.dto.chavepix.ChavePixResponse;
import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixRequest;
import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.TransacaoPix;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.ChavePixService;
import br.com.banco.domain.service.ContaService;
import br.com.banco.domain.service.TransacaoPixService;
import br.com.banco.port.out.TransacaoBacenProducerOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoPixServiceTest {

    @InjectMocks
    private TransacaoPixService service;

    @Mock
    private ContaService contaService;

    @Mock
    private TransacaoBacenProducerOutputPort transacaoBacenProducerOutputPort;

    @Mock
    ChavePixService chavePixService;


    private ChavePixMensagem buildChavePixMensagem() {
        return ChavePixMensagem.builder()
                .nome("Joao")
                .tipoChave(TipoChave.CPF_CNPJ)
                .valorChave("123456789")
                .agenciaConta("1234")
                .cpfCnpj("123456789")
                .codBanco("123")
                .build();

    }

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

    public ApiBacenResponse buildApiBacen() {
        return ApiBacenResponse.builder()
                .codBanco("123")
                .chaveExists(true)
                .tipoChave(TipoChave.CPF_CNPJ)
                .valorChave("123456789")
                .build();
    }

    public ChavePixResponse buildChavePixResponse() {
        return ChavePixResponse.builder()
                .id(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
                .valor("123456789")
                .conta(buildConta())
                .build();
    }

    public TransacaoPixRequest buildTransacaoPixRequest() {
        return TransacaoPixRequest.builder()
                .valor(BigDecimal.valueOf(100))
                .idConta(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))
                .chaveDestino("123456790")
                .build();


    }

    public TransacaoPixMensagem buildTransacaoPixMensagem() {
        return TransacaoPixMensagem.builder()
                .nome("Joao")
                .codBancoOrigem("123")
                .tipoChave(TipoChave.CPF_CNPJ)
                .valor(BigDecimal.valueOf(50))
                .chaveDestino("123456790")
                .cpfCnpj("123456789")
                .build();

    }

    public TransacaoPix buildTransacaoPix() {
        return TransacaoPix.builder()
                .name("Joao")
                .codigoBanco("123")
                .tipoChave(TipoChave.CPF_CNPJ)
                .codigoBanco("123")
                .valor(BigDecimal.valueOf(50))
                .cpf("123456789")
                .build();

    }

    public RetornoTransacaoPixMensagem buildRetornoTransacaoPixMensagem() {
        return RetornoTransacaoPixMensagem.builder()
                .transactionId(String.valueOf(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889")))
                .pixRealizado(true)
                .codBancoOrigem("123")
                .chaveDestino("123456789")
                .valor(BigDecimal.valueOf(50))
                .tipoChave(TipoChave.CPF_CNPJ)
                .cpfCnpj("123456789")
                .nome("Joao")
                .build();

    }


    @Test
    void enviaPixTest() {
        when(contaService.getById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))).thenReturn(buildConta());
        service.enviarPix(buildTransacaoPixRequest());
        verify(transacaoBacenProducerOutputPort).enviarMensagemTransacaoPix(any());
    }


    @Test
    void validarTransacaoTest() {
        when(chavePixService.getByValor("123456790")).thenReturn(buildChavePixResponse());
        when(contaService.getContaResponseById(UUID.fromString("2cc89acb-0522-4768-92cb-459d3482f889"))).thenReturn(any());
        service.validarTransacao((buildTransacaoPixMensagem()));
        verify(transacaoBacenProducerOutputPort).enviarMensagemSucessoValidacaoPix(any());
    }
}

