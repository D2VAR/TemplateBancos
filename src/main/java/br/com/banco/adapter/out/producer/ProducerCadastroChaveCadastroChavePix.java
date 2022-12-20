package br.com.banco.adapter.out.producer;


import br.com.banco.domain.dto.chavepix.ChavePixMensagem;
import br.com.banco.domain.model.ChavePix;
import br.com.banco.port.out.CadastroChavePixOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerCadastroChaveCadastroChavePix implements CadastroChavePixOutput{
    @Value("${topic.name.envio}")
    private String topico;
    private final KafkaTemplate<String, ChavePixMensagem> kafkaTemplate;

    @Override
    public void enviarMensagemCadastroChave(ChavePix chavePix){
        var mensagem = buildChavePixMensagem(chavePix);
        sendChavePixMensagemToKafka(mensagem);
    }

    private void sendChavePixMensagemToKafka(ChavePixMensagem mensagem){
        kafkaTemplate.send(topico, mensagem);
        log.info("## Mensagem de cadastro de chave enviada ao BACEN - Transaction Id: {}, Chave: {}",
                mensagem.getTransactionId(), mensagem.getValorChave());
        kafkaTemplate.flush();
    }

    private ChavePixMensagem buildChavePixMensagem(ChavePix chavePix){
        var mensagem = new ChavePixMensagem(chavePix);
        mensagem.setTransactionId(UUID.randomUUID().toString());
        return mensagem;
    }


}


