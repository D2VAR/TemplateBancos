package br.com.banco.domain.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "TransacaoPix")
public class TransacaoPix implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "VarChar(100)")
    private UUID id;

    @Column(name = "valor", updatable = false, unique = true, nullable = false, length = 100)
    private String valor;

    @Column(name = "chaveDestino", updatable = false, nullable = false)
    private String chaveDestino;

    @ManyToOne
    private Conta conta;

    public TransacaoPix(String valor, String chaveDestino, Conta conta){
        this.valor = valor;
        this.chaveDestino = chaveDestino;
        this.conta = conta;
    }

}

