package br.com.banco.adapter.out.db.repository;

import br.com.banco.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID>{
    Optional<Conta> findByAgenciaAndNumeroConta(String agencia, String numeroConta);
}

