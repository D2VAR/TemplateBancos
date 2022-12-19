package br.com.banco.domain.exceptions.port.out;

import br.com.banco.domain.model.ChavePix;

public interface BacenProducerOutputPort{

    void enviarMensagemCadastroChave(ChavePix chavePix);
}
