package br.com.banco.adapter.out.producer;

import br.com.banco.domain.dto.transacao.TransacaoPixResponse;
import br.com.banco.port.out.EnvioTransacaoPixOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerTransacaoPix implements EnvioTransacaoPixOutputPort {


    @Value("${topic.name.recebedor.envio.pix}")
    private String topic;

    private final KafkaTemplate<String, TransacaoPixResponse> kafkaTemplate;

    public ProducerTransacaoPix(KafkaTemplate<String, TransacaoPixResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarPix(TransacaoPixResponse response) {
        sendTransacaoPixToTopic(response,topic);
        log.info("#### Transacao Pix enviada - mensagem: {}", response);

    }

    private void sendTransacaoPixToTopic(TransacaoPixResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }

}
