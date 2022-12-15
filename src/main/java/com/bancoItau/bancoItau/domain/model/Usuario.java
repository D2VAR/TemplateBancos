package com.bancoItau.bancoItau.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
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
    @Column(name = "nome", nullable = false, length = 200, unique = false)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "telefone", nullable = false, unique = false)
    private Integer telefone;
    @Column(name = "email", nullable = false, unique = false)
    private String email;


    @OneToOne(mappedBy = "usuario")
    private Conta conta;

}
