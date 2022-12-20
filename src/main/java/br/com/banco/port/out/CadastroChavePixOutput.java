package br.com.banco.port.out;

import br.com.banco.domain.model.ChavePix;

public interface CadastroChavePixOutput{
    void enviarMensagemCadastroChave(ChavePix chavePix);
}
