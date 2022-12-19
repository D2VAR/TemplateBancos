package br.com.banco.adapter.out.producer;


import br.com.banco.domain.exceptions.port.out.BacenProducerOutputPort;
import br.com.banco.domain.dto.chave.ChavePixMensagem;
import br.com.banco.domain.mapper.ChavePixMapper;
import br.com.banco.domain.model.ChavePix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerCadastroChave implements BacenProducerOutputPort{
    @Value("${topic.name.envio}")
    private String topico;
    private final KafkaTemplate<String, ChavePixMensagem> kafkaTemplate;
    private final ChavePixMapper chavePixMapper;

    @Override
    public void enviarMensagemCadastroChave(ChavePix chavePix){
        ChavePixMensagem mensagem = chavePixMapper.toMensagem(chavePix);
        mensagem.setTransactionId(UUID.randomUUID().toString());
        kafkaTemplate.send(topico, mensagem);
        log.info("#### Mensagem produzida - Chave Pix: {}", mensagem);
        kafkaTemplate.flush();
    }
}


