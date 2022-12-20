package br.com.banco.service;

import br.com.banco.adapter.out.db.repository.UsuarioRepository;
import br.com.banco.domain.dto.usuario.UsuarioRequest;
import br.com.banco.domain.dto.usuario.UsuarioResponse;
import br.com.banco.domain.exceptions.UsuarioNotFoundException;
import br.com.banco.domain.model.Usuario;
import br.com.banco.domain.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    private UsuarioService service;

    private Usuario buildUsuario() {
        return Usuario.builder()
                .id(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .nome("Joao")
                .cpf("123456789")
                .build();
    }

    private UsuarioRequest buildUsuarioRequest() {
        return UsuarioRequest.builder()
                .nome("Joao")
                .cpf("123456789")
                .build();
    }

    private Usuario buildUsuarioResponse() {
        return Usuario.builder()
                .id(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
                .nome("Joao")
                .cpf("123456789")
                .build();
    }


    @Test
    void getUsuarioByIdTest() {
        when(repository.findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(Optional.of((buildUsuario())));
        Usuario result = service.getUsuariobyId(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));

        verify(repository, times(1))
                .findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));
        assertEquals(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"), result.getId());

    }

    @Test
    void getUsuarioByIdExcepitionTest() {
        when(repository.findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(Optional.empty());
        UsuarioNotFoundException ex = Assertions.assertThrows(UsuarioNotFoundException.class, () ->
                service.getUsuariobyId(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))
        );
        assertEquals("Usuario nao encontrado!", ex.getMessage());
    }

    @Test
    void getUsuarioResponseByIdTest() {
        when(repository.findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(Optional.of((buildUsuario())));
        UsuarioResponse result = service.getUsuario(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));

        verify(repository, times(1))
                .findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));
        assertEquals(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"), result.getId());

    }

    @Test
    void getUsuarioResponseByCpfTest() {
        when(repository.findByCpf("123456789"))
                .thenReturn(Optional.of((buildUsuario())));
        Usuario result = service.getUsuarioByCpf("123456789");

        verify(repository, times(1))
                .findByCpf("123456789");
        assertEquals("123456789", result.getCpf());

    }


    @Test
    void getUsuarioResponseByCpfExcepitionTest() {
        when(repository.findByCpf("123456789"))
                .thenReturn(Optional.empty());
        UsuarioNotFoundException ex = Assertions.assertThrows(UsuarioNotFoundException.class, () ->
                service.getUsuarioByCpf("123456789")
        );
        assertEquals("Usuario nao encontrado!", ex.getMessage());
    }


    @Test
    void deleteUsuarioByIdTest() {
        when(repository.findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(Optional.of((buildUsuario())));
        service.deletePorId(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));
        verify(repository, times(1))
                .deleteById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));
    }

    @Test
    void saveUsuarioTest() {
        when(repository.save(any(Usuario.class))).thenReturn(buildUsuarioResponse());
        UsuarioResponse result = service.saveUsuario(buildUsuarioRequest());
        verify(repository, times(1)).save(any(Usuario.class));
        assertEquals("Joao", result.getNome());
    }

    @Test
    void updateUsuarioTest() {
        when(repository.findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e")))
                .thenReturn(Optional.of((buildUsuario())));
        when(repository.save(any(Usuario.class))).thenReturn(buildUsuarioResponse());
        UsuarioResponse result = service.updateById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"), buildUsuarioRequest());
        verify(repository, times(1)).save(any(Usuario.class));
        assertEquals("Joao", result.getNome());
    }

    @Test
    void getUsiarioTest() {
        when(repository.findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"))).thenReturn(Optional.of(buildUsuario()));
        Usuario result = service.getUsuariobyId(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));
        verify(repository, times(1)).findById(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"));
        assertEquals(UUID.fromString("ae976368-92a7-47bf-ad0d-867ca36e821e"), result.getId());
    }
}
