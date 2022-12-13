package com.example.pixitau.model;

import com.example.pixitau.dto.UsuarioRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")

public class Usuario extends RepresentationModel<Usuario> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf_cnpj;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    public Usuario(UsuarioRequest usuarioRequest) {
        this.id = usuarioRequest.getId();
        this.cpf_cnpj = usuarioRequest.getCpf_cnpj();
        this.nome = usuarioRequest.getNome();
        this.email = usuarioRequest.getEmail();
        this.telefone = usuarioRequest.getEmail();
    }
}
