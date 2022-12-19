package br.com.banco.adapter.out.producer;

import br.com.banco.domain.dto.transacao.TransacaoValidacaoResponse;
import br.com.banco.port.out.ValidaTransacaoPixOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerValidacaoTransacaoPix implements ValidaTransacaoPixOutputPort{


    @Value("${topic.name.retorno.positivo}")

    private String topicoRetornoPositivo;
    @Value("${topic.name.retorno.negativo}")
    private String topicoRetornoNegativo;


    private final KafkaTemplate<String, TransacaoValidacaoResponse> kafkaTemplate;

    public ProducerValidacaoTransacaoPix(KafkaTemplate<String, TransacaoValidacaoResponse> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarValidacaoPositiva(TransacaoValidacaoResponse response){
        sendValidacaoPixToTopic(response, topicoRetornoPositivo);
        log.info("#### Retorno Transacao Pix positivo- mensagem: {}", response);
    }

    @Override
    public void enviarValidacaoNegativa(TransacaoValidacaoResponse response){
        sendValidacaoPixToTopic(response, topicoRetornoNegativo);
        log.info("#### Retorno Transacao Pix negativo- mensagem: {}", response);
    }


    private void sendValidacaoPixToTopic(TransacaoValidacaoResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }


}
