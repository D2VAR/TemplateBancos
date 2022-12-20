package br.com.banco.adapter.in.consumer;

import br.com.banco.domain.dto.ChavePixMensagem;
import br.com.banco.port.in.CadastroChavePixInput;
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
public class ChavePixConsumer{

    private final CadastroChavePixInput inputPort;

    @KafkaListener(id = "${spring.kafka.consumer.group-id.success}", topics = "${topic.name.retorno.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var mensagem = parseStringToChavePixMensagem(mensagemKafka);
            inputPort.cadastrarChaveInterna(mensagem);
            log.info("### Mensagem de sucesso consumida! -> Transaction Id: {}, Chave Pix: {}",
                    mensagem.getTransactionId(), mensagem.getValorChave());
            //TODO: adicionar notificacao ao cliente
        } catch (JsonProcessingException ex){
            log.error("### Erro ao processar mensagem de sucesso! -> {}, erro -> {}",
                    mensagemKafka.value(), ex.getMessage());
        } finally{
            ack.acknowledge();
        }
    }

    @KafkaListener(id = "${spring.kafka.consumer.group-id.failure}", topics = "${topic.name.retorno.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var mensagem = parseStringToChavePixMensagem(mensagemKafka);
            log.info("### Mensagem de falha consumida! -> Transaction Id: {}, Chave Pix: {}",
                    mensagem.getTransactionId(), mensagem.getValorChave());
            //TODO: adicionar notificacao ao cliente
        } catch (JsonProcessingException ex){
            log.error("### Erro ao processar mensagem de falha! -> {}, erro -> {}",
                    mensagemKafka.key(), ex.getMessage());
        } finally{
            ack.acknowledge();
        }
    }

    private ChavePixMensagem parseStringToChavePixMensagem(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        return new ObjectMapper().readValue(mensagemKafka.value(), ChavePixMensagem.class);
    }

}
