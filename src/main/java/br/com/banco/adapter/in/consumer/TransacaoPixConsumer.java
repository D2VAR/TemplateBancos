package br.com.banco.adapter.in.consumer;


import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.port.in.NotificationInputPort;
import br.com.banco.port.in.PixSender;
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
public class TransacaoPixConsumer{
    private final PixSender inputPort;
    private final NotificationInputPort notification;

    @KafkaListener(id = "${spring.kafka.consumer.group-id.envio-pix.success}", topics = "${topic.name.retorno-pagador.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var mensagem = parseStringToRetornoTransacaoPixMensagem(mensagemKafka);
            inputPort.concretizarPix(mensagem);
            log.info("### Pagador Fim Pix ### Mensagem de sucesso consumida! -> Transaction Id: {}, Valor Pix: {}, Chave Pix: {}, topico: {}",
                    mensagem.getTransactionId(), mensagem.getValor(), mensagem.getChaveDestino(), mensagemKafka.topic());
            notification.sendSuccessTransacaoPixEmail(mensagem);
        } catch (JsonProcessingException ex){
            log.error("### Pagador Fim Pix ### Erro ao processar mensagem de sucesso! -> {}, erro -> {}",
                    mensagemKafka.value(), ex.getMessage());
        } finally{
            ack.acknowledge();
        }
    }

    @KafkaListener(id = "${spring.kafka.consumer.group-id.envio-pix.failure}", topics = "${topic.name.retorno-pagador.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack){
        try{
            var mensagem = parseStringToRetornoTransacaoPixMensagem(mensagemKafka);
            log.info("### Pagador Fim Pix ### Mensagem de falha consumida! -> Transaction Id: {}, Valor Pix: {}, Chave Pix: {}, topico: {}",
                    mensagem.getTransactionId(), mensagem.getValor(), mensagem.getChaveDestino(), mensagemKafka.topic());
            notification.sendFailureTransacaoPixEmail(mensagem);
        } catch (JsonProcessingException ex){
            log.error("### Pagador Fim Pix ### Erro ao processar mensagem de falha! -> {}, erro -> {}",
                    mensagemKafka.key(), ex.getMessage());
        } finally{
            ack.acknowledge();
        }
    }

    private RetornoTransacaoPixMensagem parseStringToRetornoTransacaoPixMensagem(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        return new ObjectMapper().readValue(mensagemKafka.value(), RetornoTransacaoPixMensagem.class);
    }

}
