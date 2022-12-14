package br.com.banco.domain.dto.usuario;

import br.com.banco.domain.model.Usuario;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse{
    private UUID id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;


    public UsuarioResponse(Usuario usuario){
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
    }
}
