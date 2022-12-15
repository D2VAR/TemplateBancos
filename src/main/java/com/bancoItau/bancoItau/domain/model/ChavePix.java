package com.bancoItau.bancoItau.domain.model;

import com.bancoItau.bancoItau.domain.dto.ChavePixRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ChavePix")
public class ChavePix {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.annotations.Type(type="org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "VarChar(100)")
    private UUID id;
    private String valor;


    public ChavePix(ChavePixRequestDTO chavePixRequest) {
        this.id = chavePixRequest.getId();
        this.valor = chavePixRequest.getValor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChavePix chavePix = (ChavePix) o;
        return id.equals(chavePix.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ChavePix{" +
                "id=" + id +
                ", valor='" + valor + '\'' +
                '}';
    }

    public Conta getConta() {
        Conta conta = new Conta();
        return conta;
    }
}
