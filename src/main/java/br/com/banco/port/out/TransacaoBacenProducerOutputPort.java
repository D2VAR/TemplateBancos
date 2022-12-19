package br.com.banco.port.out;


import br.com.banco.domain.model.TransacaoPix;

public interface TransacaoBacenProducerOutputPort {
    void enviarMensagemTransacaoPix(TransacaoPix transacaoPix);
}
