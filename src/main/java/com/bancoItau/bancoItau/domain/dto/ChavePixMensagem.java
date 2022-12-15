package com.bancoItau.bancoItau.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChavePixMensagem {

    private String codBanco;
    private String numeroConta;
    private String agenciaConta;
    private String cpfCnpj;
    private String nome;
    private String tipoChave;
    private String valorChave;
}
