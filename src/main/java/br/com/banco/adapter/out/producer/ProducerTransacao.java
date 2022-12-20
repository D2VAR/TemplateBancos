package br.com.banco.adapter.out.producer;


import br.com.banco.domain.dto.TransacaoPixMensagem;
import br.com.banco.domain.model.TransacaoPix;
import br.com.banco.port.out.TransacaoBacenProducerOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerTransacao implements TransacaoBacenProducerOutputPort {
    @Value("${topic.name.pagador}")
    private String topico;
    private final KafkaTemplate<String, TransacaoPixMensagem> kafkaTemplate;

    private void sendTransacaoPixMensagemToKafka(TransacaoPixMensagem mensagem){
        kafkaTemplate.send(topico, mensagem);
        log.info("## Mensagem de transacao de Pix enviada ao BACEN - Transaction Id: {}, Chave: {}",
                mensagem.getTransactionId(), mensagem.getValorChave());
        kafkaTemplate.flush();
    }

    private TransacaoPixMensagem buildTransacaoPixMensagem(TransacaoPix transacaoPix){
        var mensagem = new TransacaoPixMensagem(transacaoPix);
        mensagem.setTransactionId(UUID.randomUUID().toString());
        return mensagem;
    }


    @Override
    public void enviarMensagemTransacaoPix(TransacaoPix transacaoPix) {
        var mensagem = buildTransacaoPixMensagem(transacaoPix);
        sendTransacaoPixMensagemToKafka(mensagem);

    }
}


