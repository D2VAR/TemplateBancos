package br.com.banco.adapter.in.consumer;


import br.com.banco.domain.dto.transacao.TransacaoPixRequest;
import br.com.banco.port.in.ValidaTransacaoPixInputPort;
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
public class ValidacaoTransacaoPixConsumer{
    private final ValidaTransacaoPixInputPort envioTransacaoPixInputPort;

    @KafkaListener(id = "id=${spring.kafka.consumer.group-id1}", topics = "${topic.name.recebedor}")
    public void listen(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            processConsumerRecord(mensagemKafka);
        } catch (JsonProcessingException ex){
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally{
            ack.acknowledge();
        }
    }

    private void processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info("#### Message consumed -> {}, topic -> {}", mensagemKafka.value(), mensagemKafka.topic());
        var request = new ObjectMapper().readValue(mensagemKafka.value(), TransacaoPixRequest.class);
        envioTransacaoPixInputPort.validarTransacaoPix(request);
    }


}

