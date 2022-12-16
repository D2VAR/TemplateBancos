package com.itau.domain.model;

import com.itau.domain.enums.TipoChave;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "ChavePix")
public class ChavePix {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "VarChar(100)")
    private UUID id;
    @Column(name = "valor", updatable = false, unique = true, nullable = false, length = 100)
    private String valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", updatable = false, nullable = false)
    private TipoChave tipo;
    @ManyToOne
    private Conta conta;

    public ChavePix(String valor, TipoChave tipo, Conta conta) {
        this.valor = valor;
        this.tipo = tipo;
        this.conta = conta;
    }
}
