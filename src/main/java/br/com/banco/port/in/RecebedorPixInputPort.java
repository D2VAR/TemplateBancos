package br.com.banco.port.in;

import br.com.banco.domain.dto.RecebedorPixMensagem;

public interface RecebedorPixInputPort {

    void validarAlteracaoSaldo(RecebedorPixMensagem recebedorPixMensagem);
}
