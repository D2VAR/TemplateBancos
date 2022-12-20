package br.com.banco.domain.service;

import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.dto.email.EmailRequest;
import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
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

    @Override
    public void sendSuccessTransacaoPixEmail(RetornoTransacaoPixMensagem mensagem){
        var email = buildTransacaoSuccessEmailRequest(mensagem);
        outputPort.sendEmail(email);
    }

    private EmailRequest buildTransacaoSuccessEmailRequest(RetornoTransacaoPixMensagem mensagem){
        var email = buildTransferBaseEmailRequest(mensagem);
        setEmailSubject(email, "Pix enviado com sucesso!");
        setEmailContent(email, generateSuccessTransferMessage(mensagem));
        return email;
    }

    private EmailRequest buildTransferBaseEmailRequest(RetornoTransacaoPixMensagem mensagem){
        var usuario = usuarioService.getUsuario(mensagem.getCpfCnpj());
        return EmailRequest.builder()
                .to(usuario.getEmail())
                .from(fromEmail)
                .build();
    }

    private String generateSuccessTransferMessage(RetornoTransacaoPixMensagem mensagem){
        return String.format("""
                        <h2><code>Maravilha %s!</code></h2>
                                                
                        <pre>
                        <code>Seu PIX para a chave: <strong>%s</strong> de valor <strong>R$%s</strong>, foi enviado!
                        <code>
                                                
                        Atenciosamente,
                        equipe Ita&uacute; Unibanco.</code>
                        </pre>
                        """,
                mensagem.getNome(),
                mensagem.getChaveDestino(),
                mensagem.getValor());
    }

    @Override
    public void sendFailureTransacaoPixEmail(RetornoTransacaoPixMensagem mensagem){
        var email = buildTransacaoFailureEmailRequest(mensagem);
        outputPort.sendEmail(email);
    }

    private EmailRequest buildTransacaoFailureEmailRequest(RetornoTransacaoPixMensagem mensagem){
        var email = buildTransferBaseEmailRequest(mensagem);
        setEmailSubject(email, "Erro no envio do pix!");
        setEmailContent(email, generateFailureTransferMessage(mensagem));
        return email;
    }

    private String generateFailureTransferMessage(RetornoTransacaoPixMensagem mensagem){
        return String.format("""
                        <h2><code>Que pena, %s!</code></h2>
                                                
                        <pre>
                        <code>Seu PIX para a chave: <strong>%s</strong> de valor <strong>%s</strong>, n&atilde;o foi enviado corretamente!
                                                
                        Mas n&atilde;o se preocupe! J&acute devolvemos seu dinheiro!
                        <code>
                                                
                        Atenciosamente,
                        equipe Ita&uacute; Unibanco.</code>
                        </pre>
                        """,
                mensagem.getNome(),
                mensagem.getChaveDestino(),
                mensagem.getValor());
    }

    @Override
    public void sendSuccessReceivePixEmail(RetornoTransacaoPixMensagem mensagem){
        var email = buildPixReceivedEmailRequest(mensagem);
        outputPort.sendEmail(email);
    }

    private EmailRequest buildPixReceivedEmailRequest(RetornoTransacaoPixMensagem mensagem){
        var email = buildTransferBaseEmailRequest(mensagem);
        setEmailSubject(email, "Pix recebido!");
        setEmailContent(email, generatePixReceivedMessage(mensagem));
        return email;
    }

    private String generatePixReceivedMessage(RetornoTransacaoPixMensagem mensagem){
        return String.format("""
                        <h2><code>Que beleza hein %s!</code></h2>
                                                
                        <pre>
                        <code>Voc&ecirc; acabou de receber um pix de: <strong>R$%s</strong> do banco <strong>%s</strong>!
                        <code>
                        V&ecirc; se aproveita essa grana em!
                                               
                        Atenciosamente,
                        equipe Ita&uacute; Unibanco.</code>
                        </pre>
                        """,
                mensagem.getNome(),
                mensagem.getValor(),
                mensagem.getCodBancoOrigem());
    }
}
