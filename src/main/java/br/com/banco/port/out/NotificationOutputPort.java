package br.com.banco.port.out;

import br.com.banco.domain.dto.EmailRequest;

public interface NotificationOutputPort{
    void sendEmail(EmailRequest email);
}
