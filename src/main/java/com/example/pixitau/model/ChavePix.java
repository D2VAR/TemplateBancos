package com.example.pixitau.model;

import com.example.pixitau.dto.ChavePixRequest;
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
    private UUID id;

    private String valor;


    public ChavePix(ChavePixRequest chavePixRequest) {
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
}
