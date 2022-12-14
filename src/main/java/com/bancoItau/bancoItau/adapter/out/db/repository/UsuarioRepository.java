package com.bancoItau.bancoItau.adapter.out.db.repository;

import com.bancoItau.bancoItau.domain.model.Conta;
import com.bancoItau.bancoItau.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}

