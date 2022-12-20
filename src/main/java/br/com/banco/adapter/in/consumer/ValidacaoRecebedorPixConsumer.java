package br.com.banco.adapter.in.consumer;

import br.com.banco.domain.dto.transacaopix.TransacaoPixMensagem;
import br.com.banco.port.in.PixReceiver;
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
public class ValidacaoRecebedorPixConsumer{
    private final PixReceiver inputPort;

    @KafkaListener(id = "${spring.kafka.consumer.group-id.recebedor-pix-validacao}", topics = "${topic.name.recebedor}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var recebedorPixMensagem = processConsumerRecord(mensagemKafka);
            inputPort.validarTransacao(recebedorPixMensagem);

        } catch (Exception ex){
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());

        } finally{
            ack.acknowledge();
        }
    }

    private TransacaoPixMensagem processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info(String.format("#### Mensagem Consumida -> %s, topic -> %s",
                mensagemKafka.value(), mensagemKafka.topic()));
        return new ObjectMapper().readValue(mensagemKafka.value(), TransacaoPixMensagem.class);
    }
}