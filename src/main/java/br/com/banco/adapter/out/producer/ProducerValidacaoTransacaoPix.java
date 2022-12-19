package br.com.banco.adapter.out.producer;

import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;
import br.com.banco.port.out.ValidacaoTransacaoOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerValidacaoTransacaoPix implements ValidacaoTransacaoOutputPort {


    @Value("${topic.name.resultado.success}")
    private String topicTransacaoSuccess;
    @Value("${topic.name.resultado.fail}")
    private String topicTransacaoFailure;
    @Value("${topic.name.recebedor.retorno.success}")
    private String topicRetornoSuccess;
    @Value("${topic.name.recebedor.retorno.fail}")
    private String topicRetornoFailure;

    private final KafkaTemplate<String, TransacaoValidacaoResponse> kafkaTemplate;

    public ProducerValidacaoTransacaoPix(KafkaTemplate<String, TransacaoValidacaoResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notificaSucesso(TransacaoValidacaoResponse response) {
        sendTransacaoPixResponseToTopic(response, topicRetornoSuccess);
        sendTransacaoPixResponseToTopic(response, topicTransacaoSuccess);
        log.info("#### Retorno Transacao Pix Sucesso- mensagem: {}", response);
    }

    @Override
    public void notificaFalha(TransacaoValidacaoResponse response) {
        sendTransacaoPixResponseToTopic(response, topicRetornoFailure);
        sendTransacaoPixResponseToTopic(response, topicTransacaoFailure);
        log.info("#### Retorno Transacao Pix Falha - mensagem: {}", response);
    }

    private void sendTransacaoPixResponseToTopic(TransacaoValidacaoResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }

}
