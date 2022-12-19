package br.com.banco.domain.service;

import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.domain.dto.EmailRequest;
import br.com.banco.port.in.NotificationInputPort;
import br.com.banco.port.out.NotificationOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationInputPort{
    private final NotificationOutputPort outputPort;
    private final UsuarioService usuarioService;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendSuccessChavePixCadastradaEmail(ChavePixMensagem mensagem){
        var email = buildCadastroSuccessEmailRequest(mensagem);
        outputPort.sendEmail(email);
    }

    private EmailRequest buildCadastroSuccessEmailRequest(ChavePixMensagem mensagem){
        var email = buildBaseEmailRequest(mensagem);
        setEmailSubject(email, "Chave Pix cadastrada com sucesso!");
        setEmailContent(email, generateSuccessMessage(mensagem));
        return email;
    }

    private EmailRequest buildBaseEmailRequest(ChavePixMensagem mensagem){
        var usuario = usuarioService.getUsuario(mensagem.getCpfCnpj());
        return EmailRequest.builder()
                .to(usuario.getEmail())
                .from(fromEmail)
                .build();
    }

    private String generateSuccessMessage(ChavePixMensagem mensagem){
        return String.format("""
                        <h2><code>Parab&eacute;ns %s!</code></h2>

                        <pre>

                        <code>Sua chave pix de tipo: <strong>%s</strong> e valor <strong>%s</strong>, foi cadastrada com sucesso!

                        Atenciosamente,
                        equipe Ita&uacute; Unibanco.</code>
                        </pre>
                        """,
                mensagem.getNome(),
                mensagem.getTipoChave(),
                mensagem.getValorChave());
    }

    private void setEmailSubject(EmailRequest email, String s){
        email.setSubject(s);
    }

    private void setEmailContent(EmailRequest email, String s){
        email.setContent(s);
    }

    @Override
    public void sendFailureChavePixCadastradaEmail(ChavePixMensagem mensagem){
        var email = buildCadastroFailureEmailRequest(mensagem);
        outputPort.sendEmail(email);
    }

    private EmailRequest buildCadastroFailureEmailRequest(ChavePixMensagem mensagem){
        var email = buildBaseEmailRequest(mensagem);
        setEmailSubject(email, "Falha no cadastro de chave pix!");
        setEmailContent(email, generateFailureMessage(mensagem));
        return email;
    }

    private String generateFailureMessage(ChavePixMensagem mensagem){
        return String.format("""
                        <h2><code>Poxa %s, que pena!</code></h2>
                                                
                        <pre>
                        <code>Sua chave pix de tipo: <strong>%s</strong> e valor <strong>%s</strong>, n&atilde;o foi cadastrada corretamente!
                        Pedimos que tente novamente assim que poss&iacute;vel!</code>
                        <code>
                        Atenciosamente,
                        equipe Ita&uacute; Unibanco.</code>
                        </pre>
                        """,
                mensagem.getNome(),
                mensagem.getTipoChave(),
                mensagem.getValorChave());
    }
}
