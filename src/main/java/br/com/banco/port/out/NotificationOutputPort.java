package br.com.banco.port.out;

import br.com.banco.domain.dto.email.EmailRequest;

public interface NotificationOutputPort{
    void sendEmail(EmailRequest email);
}
