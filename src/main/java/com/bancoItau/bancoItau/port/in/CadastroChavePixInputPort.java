package com.bancoItau.bancoItau.port.in;

import com.bancoItau.bancoItau.domain.dto.ChavePixMensagem;

public interface CadastroChavePixInputPort {
    void cadastrarChave(ChavePixMensagem chavePixMensagem);
}
