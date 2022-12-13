package com.example.pixitau.mapper;


import com.example.pixitau.dto.UsuarioRequest;
import com.example.pixitau.model.Usuario;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel ="spring" )
@Component
public interface UsuarioMapper {

   public Usuario toModel (UsuarioRequest usuarioRequest);
}
