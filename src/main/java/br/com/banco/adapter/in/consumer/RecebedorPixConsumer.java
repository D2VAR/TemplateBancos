package br.com.banco.adapter.in.consumer;

import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.port.in.NotificationInputPort;
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
public class RecebedorPixConsumer{
    private final PixReceiver inputPort;
    private final NotificationInputPort notification;


    @KafkaListener(id = "${spring.kafka.consumer.group-id.recebedor-pix.success}", topics = "${topic.name.fim.transacao.pix.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var mensagem = processConsumerRecord(mensagemKafka);
            inputPort.consolidarRecebimentoPix(mensagem);
            notification.sendSuccessTransacaoPixEmail(mensagem);

        } catch (Exception ex){
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());

        } finally{
            ack.acknowledge();
        }
    }

    @KafkaListener(id = "${spring.kafka.consumer.group-id.recebedor-pix.failure}", topics = "${topic.name.fim.transacao.pix.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var mensagem = processConsumerRecord(mensagemKafka);
            inputPort.consolidarRecebimentoPix(mensagem);
            notification.sendFailureTransacaoPixEmail(mensagem);
        } catch (Exception ex){
            log.error("#### Erro Consumer Mensagem -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally{
            ack.acknowledge();
        }
    }

    private RetornoTransacaoPixMensagem processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info(String.format("#### Mensagem Consumida -> %s, topic -> %s",
                mensagemKafka.value(), mensagemKafka.topic()));
        return new ObjectMapper().readValue(mensagemKafka.value(), RetornoTransacaoPixMensagem.class);
    }
}