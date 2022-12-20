package br.com.banco.adapter.in.consumer;


import br.com.banco.domain.dto.TransacaoPixMensagem;
import br.com.banco.port.in.TransacaoPixInputPort;
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
    private final TransacaoPixInputPort inputPort;

    //TODO: Trocartopicos
    @KafkaListener(id = "${spring.kafka.consumer.group-id.success}", topics = "${topic.retorno-pagador.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var mensagem = parseStringToChavePixMensagem(mensagemKafka);
            inputPort.validarTransacao(mensagem);
            log.info("### Mensagem de sucesso consumida! -> Transaction Id: {}, Valor Pix: {}, Chave Pix: {}",
                    mensagem.getTransactionId(), mensagem.getValor(), mensagem.getChaveDestino());
            //TODO: adicionar notificacao ao cliente
        } catch (JsonProcessingException ex) {
            log.error("### Erro ao processar mensagem de sucesso! -> {}, erro -> {}",
                    mensagemKafka.value(), ex.getMessage());
        } finally {
            ack.acknowledge();
        }
    }

    @KafkaListener(id = "${spring.kafka.consumer.group-id.failure}", topics = "${topic.retorno-pagador.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var mensagem = parseStringToChavePixMensagem(mensagemKafka);
            log.info("### Mensagem de falha consumida! -> Transaction Id: {}, Valor Pix: {}, Chave Pix: {}",
                    mensagem.getTransactionId(), mensagem.getValor(), mensagem.getChaveDestino());
            //TODO: adicionar notificacao ao cliente
        } catch (JsonProcessingException ex) {
            log.error("### Erro ao processar mensagem de falha! -> {}, erro -> {}",
                    mensagemKafka.key(), ex.getMessage());
        } finally {
            ack.acknowledge();
        }
    }

    private TransacaoPixMensagem parseStringToChavePixMensagem(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException {
        return new ObjectMapper().readValue(mensagemKafka.value(), TransacaoPixMensagem.class);
    }

}
