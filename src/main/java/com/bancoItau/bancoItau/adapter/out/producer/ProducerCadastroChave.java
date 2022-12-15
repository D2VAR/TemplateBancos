package com.bancoItau.bancoItau.adapter.out.producer;

import com.bancoItau.bancoItau.domain.dto.ChavePixMensagem;
import com.bancoItau.bancoItau.domain.mapper.ChavePixMapper;
import com.bancoItau.bancoItau.domain.model.ChavePix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerCadastroChave {

    @Value("${topic.name.envio}")
    private String topico;

    private final KafkaTemplate<String, ChavePixMensagem> kafkaTemplate;
    private final ChavePixMapper chavePixMapper;

    public void cadastrarChavePix(ChavePix novaChavePix) {
        ChavePixMensagem mensagem = chavePixMapper.toMensagem(novaChavePix);

        kafkaTemplate.send(topico, mensagem);
        log.info("#### Mensagem produzida - Chave Pix: {}", mensagem);

        kafkaTemplate.flush();
    }
}


