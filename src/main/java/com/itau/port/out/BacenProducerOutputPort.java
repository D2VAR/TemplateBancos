package com.itau.port.out;

import com.itau.domain.model.ChavePix;

public interface BacenProducerOutputPort{

    void enviarMensagemCadastroChave(ChavePix chavePix);
}
