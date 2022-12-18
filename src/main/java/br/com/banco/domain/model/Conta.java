package br.com.banco.domain.model;

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
public class Conta{

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id", updatable = false, unique = true, nullable = false, columnDefinition = "varChar(100)")
    private UUID id;
    private String agencia;
    private String numero;
    private BigDecimal saldo;
    @OneToOne
    private Usuario usuario;

    public String getBanco(){
        return "341";
    }

    public Conta(String agencia, String numero, Usuario usuario){
        this.agencia = agencia;
        this.numero = numero;
        this.usuario = usuario;
    }
}
