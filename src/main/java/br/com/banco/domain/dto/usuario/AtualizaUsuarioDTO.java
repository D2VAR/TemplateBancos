package br.com.banco.domain.dto.usuario;

import lombok.Data;

@Data
public class AtualizaUsuarioDTO {
    private String nome;
    private String telefone;
    private String email;

}