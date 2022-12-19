package br.com.banco.service;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock
    private Conta repository;

    Conta conta;
    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(UUID.randomUUID(), "Joao", "12345678910", "11457896366", "joao@joao.teste");
        conta = new Conta("1234", "1234567", usuario);
    }

    @Test
    public void erarNovaContaTest() {
        Conta conta =


    }

}
