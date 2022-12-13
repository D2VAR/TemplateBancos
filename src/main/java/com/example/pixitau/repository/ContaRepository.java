package com.example.pixitau.repository;

import com.example.pixitau.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaRepository extends CrudRepository<Conta, UUID> {
}
