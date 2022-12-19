package br.com.banco.adapter.out.db.repository;

import br.com.banco.domain.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, UUID>{
    Optional<ChavePix> findByValor(String valor);
}
