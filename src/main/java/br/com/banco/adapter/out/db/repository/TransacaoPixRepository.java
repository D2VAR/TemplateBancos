package br.com.banco.adapter.out.db.repository;

import br.com.banco.domain.model.TransacaoPix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoPixRepository extends JpaRepository<TransacaoPix, UUID> {
}
