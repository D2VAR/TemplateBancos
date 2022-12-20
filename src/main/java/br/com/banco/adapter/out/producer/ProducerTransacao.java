package br.com.banco.adapter.out.producer;


import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixMensagem;
import br.com.banco.domain.model.TransacaoPix;
import br.com.banco.port.out.TransacaoBacenProducerOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerTransacao implements TransacaoBacenProducerOutputPort{
    @Value("${topic.name.pagador}")
    private String topicoEnvioPix;
    @Value("${topic.name.envio-recebedor.success}")
    private String topicoEnvioValidacaoSucesso;
    @Value("${topic.name.envio-recebedor.failure}")
    private String topicoEnvioValidacaoFalha;
    private final KafkaTemplate<String, TransacaoPixMensagem> kafkaTemplateEnvioPix;
    private final KafkaTemplate<String, RetornoTransacaoPixMensagem> kafkaTemplateEnvioRecebedor;


    @Override
    public void enviarMensagemTransacaoPix(TransacaoPix transacaoPix){
        var mensagem = buildTransacaoPixMensagem(transacaoPix);
        sendTransacaoPixMensagemToKafka(mensagem);

    }

    private TransacaoPixMensagem buildTransacaoPixMensagem(TransacaoPix transacaoPix){
        var mensagem = new TransacaoPixMensagem(transacaoPix);
        mensagem.setTransactionId(UUID.randomUUID().toString());
        return mensagem;
    }

    private void sendTransacaoPixMensagemToKafka(TransacaoPixMensagem mensagem){
        kafkaTemplateEnvioPix.send(topicoEnvioPix, mensagem);
        log.info("## Mensagem de transacao de Pix enviada ao BACEN - Transaction Id: {}, Chave: {}",
                mensagem.getTransactionId(), mensagem.getChaveDestino());
        kafkaTemplateEnvioPix.flush();
    }

    @Override
    public void enviarMensagemErroValidacaoPix(RetornoTransacaoPixMensagem mensagem){
        sendRetornoTransacaoPixMensagemToKafka(topicoEnvioValidacaoSucesso, mensagem);
    }

    private void sendRetornoTransacaoPixMensagemToKafka(String topico, RetornoTransacaoPixMensagem mensagem){
        kafkaTemplateEnvioRecebedor.send(topico, mensagem);
        log.info("## Mensagem de validacao de Pix enviada ao BACEN - Transaction Id: {}, Chave: {}",
                mensagem.getTransactionId(), mensagem.getChaveDestino());
        kafkaTemplateEnvioPix.flush();
    }

    @Override
    public void enviarMensagemSucessoValidacaoPix(RetornoTransacaoPixMensagem mensagem){
        sendRetornoTransacaoPixMensagemToKafka(topicoEnvioValidacaoFalha, mensagem);
    }
}


