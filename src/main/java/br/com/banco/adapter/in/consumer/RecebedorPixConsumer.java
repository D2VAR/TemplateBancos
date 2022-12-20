package br.com.banco.adapter.in.consumer;

import br.com.banco.domain.dto.RecebedorPixMensagem;
import br.com.banco.port.in.RecebedorPixInputPort;
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
public class RecebedorPixConsumer{
    private final RecebedorPixInputPort inputPort;

    @KafkaListener(id = "${spring.kafka.consumer.group-id.success}", topics = "${topic.fim.transacao.pix.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var recebedorPixMensagem = processConsumerRecord(mensagemKafka);
            inputPort.validarAlteracaoSaldo(recebedorPixMensagem);

        } catch (Exception ex){
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());

        } finally{
            ack.acknowledge();
        }
    }

    @KafkaListener(id = "${spring.kafka.consumer.group-id.failure}", topics = "${topic.fim.transacao.pix.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var recebedorPixMessagem = processConsumerRecord(mensagemKafka);
            inputPort.validarAlteracaoSaldo(recebedorPixMessagem);
        } catch (Exception ex){
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally{
            ack.acknowledge();
        }
    }

    private RecebedorPixMensagem processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info(String.format("#### Mensagem Consumida -> %s, topic -> %s",
                mensagemKafka.value(), mensagemKafka.topic()));
        return new ObjectMapper().readValue(mensagemKafka.value(), RecebedorPixMensagem.class);
    }
}