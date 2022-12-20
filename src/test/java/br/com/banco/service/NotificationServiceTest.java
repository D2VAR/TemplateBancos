package br.com.banco.service;

import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.dto.email.EmailRequest;
import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.dto.usuario.UsuarioResponse;
import br.com.banco.domain.enums.TipoChave;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.NotificationService;
import br.com.banco.domain.service.UsuarioService;
import br.com.banco.port.in.NotificationInputPort;
import br.com.banco.port.out.NotificationOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService service;

    @Mock
    private NotificationOutputPort outputPort;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private NotificationInputPort inputPort;


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

    private EmailRequest buildEmailRequest() {
        return EmailRequest.builder()
                .to("teste@teste.com")
                .from("testefrom@testefrom.com")
                .content("teste")
                .subject("teste")
                .attachFileRoute("teste")
                .build();

    }

    private Usuario buildUsuario() {
        return Usuario.builder()
                .id(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .nome("Joao")
                .cpf("123456789")
                .email("testefrom@testefrom.com")
                .telefone("123456789")
                .build();
    }

    private UsuarioResponse buildUsuarioResponse() {
        return UsuarioResponse.builder()
                .id(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .nome("Joao")
                .cpf("123456789")
                .email("testefrom@testefrom.com")
                .build();
    }

    private NotificationOutputPort buildNotificationOutputPort() {
        return new NotificationOutputPort() {
            @Override
            public void sendEmail(EmailRequest email) {
                System.out.println("teste");
            }
        };

    }

    private RetornoTransacaoPixMensagem buildRetornoTransacaoPixMensagem() {
        return RetornoTransacaoPixMensagem.builder()
                .pixRealizado(true)
                .nome("Joao")
                .cpfCnpj("123456789")
                .tipoChave(TipoChave.CPF_CNPJ)
                .chaveDestino("123456789")
                .valor(BigDecimal.valueOf(50.0))
                .codBancoOrigem("123")
                .build();
    }


    @Test
    void sendSuccessChavePixCadastradaEmailTest() {
        when(usuarioService.getUsuario(buildUsuarioResponse().getCpf())).thenReturn(buildUsuarioResponse());
        service.sendSuccessChavePixCadastradaEmail(buildChavePixMensagem());
        verify(outputPort, times(1)).sendEmail(any());
    }

    @Test
    void sendFailureChavePixCadastradaEmailTest() {
        when(usuarioService.getUsuario(buildUsuarioResponse().getCpf())).thenReturn(buildUsuarioResponse());
        service.sendFailureChavePixCadastradaEmail(buildChavePixMensagem());
        verify(outputPort, times(1)).sendEmail(any());
    }

    @Test
    void sendSucessTransacaoPixEmailTest() {
        when(usuarioService.getUsuario(buildUsuarioResponse().getCpf())).thenReturn(buildUsuarioResponse());
        service.sendSuccessTransacaoPixEmail(buildRetornoTransacaoPixMensagem());
        verify(outputPort, times(1)).sendEmail(any());
    }

    @Test
    void sendFailureTransacaoPixEmailTest() {
        when(usuarioService.getUsuario(buildUsuarioResponse().getCpf())).thenReturn(buildUsuarioResponse());
        service.sendFailureTransacaoPixEmail(buildRetornoTransacaoPixMensagem());
        verify(outputPort, times(1)).sendEmail(any());
    }

    @Test
    void sendSucessReceivePixEmailTest() {
        when(usuarioService.getUsuario(buildUsuarioResponse().getCpf())).thenReturn(buildUsuarioResponse());
        service.sendSuccessReceivePixEmail(buildRetornoTransacaoPixMensagem());
        verify(outputPort, times(1)).sendEmail(any());
    }

}