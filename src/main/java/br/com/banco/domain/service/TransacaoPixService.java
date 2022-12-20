package br.com.banco.domain.service;

import br.com.banco.domain.dto.conta.ContaResponse;
import br.com.banco.domain.dto.transacaopix.RecebedorPixMensagem;
import br.com.banco.domain.dto.transacaopix.RetornoTransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixMensagem;
import br.com.banco.domain.dto.transacaopix.TransacaoPixRequest;
import br.com.banco.domain.exceptions.ChavePixNotFoundException;
import br.com.banco.domain.exceptions.ContaNotFoundException;
import br.com.banco.domain.model.TransacaoPix;
import br.com.banco.port.in.PixReceiver;
import br.com.banco.port.in.PixSender;
import br.com.banco.port.out.TransacaoBacenProducerOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoPixService implements PixSender, PixReceiver{
    private final ContaService contaService;
    private final TransacaoBacenProducerOutputPort transacaoBacenProducerOutputPort;
    private final ChavePixService chavePixService;

    @Override
    public void enviarPix(TransacaoPixRequest request){
        var transacaoPix = transacaoPixRequestToModel(request);
        chavePixService.validarExistenciaChaveDestinoPixBacen(request);
        transacaoBacenProducerOutputPort.enviarMensagemTransacaoPix(transacaoPix);
        contaService.debitarConta(request.getIdConta(), request.getValor());

    }

    @Override
    public void concretizarPix(RetornoTransacaoPixMensagem retorno){
        if (retorno.isPixRealizado()){
            log.info("### Pix enviado com sucesso! Transaction id - {}", retorno.getTransactionId());
        } else{
            log.info("### Erro no envio do pix! Transaction id - {}", retorno.getTransactionId());
            //TODO: devolver dinheiro no caso triste
        }

    }

    private TransacaoPix transacaoPixRequestToModel(TransacaoPixRequest request){
        var conta = contaService.getById(request.getIdConta());
        return new TransacaoPix(request.getChaveDestino(), request.getTipoChave(), request.getValor(), conta);
    }


    @Override
    public void validarTransacao(TransacaoPixMensagem transacaoPixMensagem){
        var retorno = new RetornoTransacaoPixMensagem(transacaoPixMensagem);
        try{
            getContaFromChavePix(transacaoPixMensagem.getChaveDestino());
            retorno.setPixRealizado(true);
            transacaoBacenProducerOutputPort.enviarMensagemSucessoValidacaoPix(retorno);
        } catch (ChavePixNotFoundException | ContaNotFoundException e){
            transacaoBacenProducerOutputPort.enviarMensagemErroValidacaoPix(retorno);
        }
    }

    private ContaResponse getContaFromChavePix(String valorChave){
        var chavePix = chavePixService.getByValor(valorChave);
        return contaService.getContaResponseById(chavePix.getConta().getId());
    }

    @Override
    public void verificarRecebimentoPix(RecebedorPixMensagem recebedorPixMensagem){
        try{
            if (recebedorPixMensagem.isPixRealizado()){
                var conta = getContaFromChavePix(recebedorPixMensagem.getChaveDestino());
                contaService.creditarConta(conta.getId(), recebedorPixMensagem.getValorTransferencia());
            }
        } catch (Exception e){
            log.error("### Erro ao consolidar pix! - Transaction id {}, Chave Pix: {}",
                    recebedorPixMensagem.getTransactionId(), recebedorPixMensagem.getChaveDestino());
        }
    }
}