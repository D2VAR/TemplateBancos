package br.com.banco.adapter.in.consumer;

import br.com.banco.domain.dto.transacao.TransacaoValidacaoRequest;
import br.com.banco.port.in.ValidacaoTransacaoInputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidacaoTransacaoPixConsumer {


    private final ValidacaoTransacaoInputPort validacaoTransacaoInputPort;

    @KafkaListener(id="${spring.kafka.consumer.group-id1}", topics = "${topic.name.recebedor.retorno.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var request = processConsumerRecord(mensagemKafka);
            validacaoTransacaoInputPort.retornarSucesso(request);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally {
            ack.acknowledge();
        }
    }

    @KafkaListener(id="${spring.kafka.consumer.group-id1}", topics = "${topic.name.recebedor.retorno.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var request = processConsumerRecord(mensagemKafka);
            validacaoTransacaoInputPort.retornarFalha(request);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally {
            ack.acknowledge();
        }
    }

    private TransacaoValidacaoRequest processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info("#### Message consumed -> {}, topic -> {}", mensagemKafka.value(), mensagemKafka.topic());
        var request = new ObjectMapper().readValue(mensagemKafka.value(), TransacaoValidacaoRequest .class);
        return request;
    }

}
