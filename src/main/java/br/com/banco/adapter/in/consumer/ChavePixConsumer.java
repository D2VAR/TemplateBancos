package br.com.banco.adapter.in.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.banco.domain.dto.ChavePixMensagem;
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
public class ChavePixConsumer{

    private final CadastroChavePixInputPort inputPort;

    @KafkaListener(id = "${spring.kafka.consumer.group-id1}", topics = "${topic.name.retorno.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        consumeSuccessMessage(mensagemKafka);
        ack.acknowledge();
    }

    private void consumeSuccessMessage(ConsumerRecord<String, String> mensagemKafka){
        try{
            log.info("#### Consumindo mensagem de sucesso! -> {}, topic -> {}",
                    mensagemKafka.value(), mensagemKafka.topic());
            ChavePixMensagem chavePixMensagem = parseStringToChavePixMensagem(mensagemKafka);
            inputPort.cadastrarChaveInterna(chavePixMensagem);
        } catch (JsonProcessingException ex){
            log.error("#### Erro ao processar mensagem de sucesso! -> {}, erro -> {}",
                    mensagemKafka.value(), ex.getMessage());
        }
    }

    private ChavePixMensagem parseStringToChavePixMensagem(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        return new ObjectMapper().readValue(mensagemKafka.value(), ChavePixMensagem.class);
    }

    @KafkaListener(id = "${spring.kafka.consumer.group-id2}", topics = "${topic.name.retorno.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        consumeFailureMessage(mensagemKafka);
        ack.acknowledge();
    }

    private void consumeFailureMessage(ConsumerRecord<String, String> mensagemKafka){
        try{
            log.info("#### Consumindo mensagem de falha! -> {}, topic -> {}",
                    mensagemKafka.value(), mensagemKafka.topic());
            parseStringToChavePixMensagem(mensagemKafka);
        } catch (JsonProcessingException ex){
            log.error("#### Erro ao processar mensagem de falha! -> {}, erro -> {}",
                    mensagemKafka.value(), ex.getMessage());
        }
    }
}
