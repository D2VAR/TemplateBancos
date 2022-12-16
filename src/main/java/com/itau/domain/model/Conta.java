package com.itau.domain.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "varChar(100)")
    private UUID id;
    private String agencia;
    private String numeroConta;
    private BigDecimal saldo;
    @OneToOne
    private Usuario usuario;

}
