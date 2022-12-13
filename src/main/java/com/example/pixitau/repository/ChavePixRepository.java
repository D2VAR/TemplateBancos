package com.example.pixitau.repository;

import com.example.pixitau.model.ChavePix;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChavePixRepository extends CrudRepository<ChavePix, UUID> {
}
