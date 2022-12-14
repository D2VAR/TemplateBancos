package com.bancoItau.bancoItau.adapter.out.db.repository;

import com.bancoItau.bancoItau.domain.model.ChavePix;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChavePixRepository extends CrudRepository<ChavePix, UUID> {
}
