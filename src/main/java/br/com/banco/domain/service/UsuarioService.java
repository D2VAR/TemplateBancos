package br.com.banco.domain.service;

import br.com.banco.adapter.out.db.repository.UsuarioRepository;
import br.com.banco.domain.dto.UsuarioRequest;
import br.com.banco.domain.dto.UsuarioResponse;
import br.com.banco.domain.exceptions.UsuarioNotFoundException;
import br.com.banco.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService{
    private final UsuarioRepository usuarioRepository;


    public UsuarioResponse getUsuario(UUID id){
        var usuario = getUsuariobyId(id);
        return buildUsuarioResponse(usuario);
    }

    public UsuarioResponse getUsuario(String cpf){
        var usuario = getUsuarioByCpf(cpf);
        return buildUsuarioResponse(usuario);
    }

    public Usuario getUsuarioByCpf(String cpf){
        return usuarioRepository.findByCpf(cpf).orElseThrow(
                () -> new UsuarioNotFoundException("Usuario nao encontrado!"));
    }

    public Usuario getUsuariobyId(UUID id){
        return usuarioRepository.findById(id).orElseThrow(
                () -> new UsuarioNotFoundException("Usuario nao encontrado!"));
    }

    private static UsuarioResponse buildUsuarioResponse(Usuario usuario){
        return new UsuarioResponse(usuario);
    }

    public UsuarioResponse saveUsuario(UsuarioRequest request){
        var usuarioSalvo = usuarioRepository.save(buildUsuario(request));
        return buildUsuarioResponse(usuarioSalvo);
    }

    private static Usuario buildUsuario(UsuarioRequest request){
        return Usuario.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .telefone(request.getTelefone())
                .email(request.getEmail())
                .build();
    }

    public void deletePorId(UUID id){
        getUsuariobyId(id);
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponse updateById(UUID id, UsuarioRequest request){
        var usuario = getUsuariobyId(id);
        validateAndUpdateAttributes(request, usuario);
        var usuarioUpdated = usuarioRepository.save(usuario);
        return buildUsuarioResponse(usuarioUpdated);
    }

    private void validateAndUpdateAttributes(UsuarioRequest request, Usuario usuario){
        if (notNullAndNotBlank(request.getNome()))
            usuario.setNome(request.getNome());

        if (notNullAndNotBlank(request.getTelefone()))
            usuario.setNome(request.getTelefone());

        if (notNullAndNotBlank(request.getEmail()))
            usuario.setNome(request.getEmail());
    }

    private boolean notNullAndNotBlank(String attribute){
        return Objects.nonNull(attribute) && !attribute.isBlank();
    }

}
