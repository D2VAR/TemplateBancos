package br.com.banco.adapter.in.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.banco.domain.dto.chave.ChavePixMensagem;
import br.com.banco.port.in.CadastroChavePixInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChavePixConsumer {

    private final CadastroChavePixInputPort inputPort;

    @KafkaListener(id="${spring.kafka.consumer.group-id1}", topics = "${topic.name.retorno.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            log.info(String.format("#### Mensagem Consumida -> %s, topic -> %s",
                    mensagemKafka.value(), mensagemKafka.topic()));
            ChavePixMensagem chavePixMensagem = new ObjectMapper().readValue(mensagemKafka.value(), ChavePixMensagem.class);
            inputPort.cadastrarChaveInterna(chavePixMensagem);

        } catch (Exception ex) {
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());

        } finally {
            ack.acknowledge();
        }
    }

    @KafkaListener(id="${spring.kafka.consumer.group-id2}", topics = "${topic.name.retorno.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            log.info(String.format("#### Mensagem Consumida -> %s, topic -> %s",
                    mensagemKafka.value(), mensagemKafka.topic()));
            new ObjectMapper().readValue(mensagemKafka.value(), ChavePixMensagem.class);

        } catch (Exception ex) {
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally {
            ack.acknowledge();
        }
    }
}
