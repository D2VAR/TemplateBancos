package br.com.banco.adapter.out.mail;

import br.com.banco.domain.dto.EmailRequest;
import br.com.banco.port.out.NotificationOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
@Slf4j
public class EnviaEmail implements NotificationOutputPort{
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequest email){
        try{
            var message = buildEmailMessage(email);
            mailSender.send(message);
            log.info("#### Email enviado!");
        } catch (Exception e){
            log.error("#### Erro ao enviar email! - Error: {}, Stacktrace: {}", e.getMessage(), e.getStackTrace());
        }
    }

    private MimeMessage buildEmailMessage(EmailRequest email) throws MessagingException, IOException{
        var msg = mailSender.createMimeMessage();
        setMessageProperties(email, msg);
        return msg;
    }

    private void setMessageProperties(EmailRequest email, MimeMessage msg) throws MessagingException, IOException{
        var helper = new MimeMessageHelper(msg);
        setHelperProperties(email, helper);
        setMessageContent(email, msg);
    }

    private void setHelperProperties(EmailRequest email, MimeMessageHelper helper) throws MessagingException{
        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
    }

    private void setMessageContent(EmailRequest email, MimeMessage msg) throws MessagingException, IOException{
        var multipart = buildMultipartContent(email);
        msg.setContent(multipart);
    }

    private MimeMultipart buildMultipartContent(EmailRequest email) throws MessagingException, IOException{
        var multipart = new MimeMultipart();

        var messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(email.getContent(), "text/html");

        setAttachment(email, multipart);

        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }

    private void setAttachment(EmailRequest email, MimeMultipart multipart) throws MessagingException, IOException{
        if (Objects.nonNull(email.getAttachFileRoute()) && !email.getAttachFileRoute().isBlank()){
            var attachment = new MimeBodyPart();
            attachment.attachFile(email.getAttachFileRoute());
            multipart.addBodyPart(attachment);
        }
    }
}
