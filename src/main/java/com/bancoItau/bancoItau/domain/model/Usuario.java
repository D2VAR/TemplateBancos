package com.bancoItau.bancoItau.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "nome", nullable = false, length = 200, unique = false)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "telefone", nullable = false, unique = false)
    private int telefone;
    @Column(name = "email", nullable = false, unique = false)
    private String email;


    //@OneToOne(mappedBy = "usuario")
    //private Conta conta;

}
