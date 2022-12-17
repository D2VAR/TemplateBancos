package com.itau.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "varChar(100)")
    private UUID id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true, length = 20)
    private String cpf;
    @Column(name = "telefone", nullable = false)
    private String telefone;
    @Column(name = "email", nullable = false)
    private String email;

    public Object getBanco() {
        return "341";
    }
}
