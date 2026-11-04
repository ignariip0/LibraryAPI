package com.github.ignariip0.libraryapi.controller.mappers;

import com.github.ignariip0.libraryapi.controller.dto.UsuarioDTO;
import com.github.ignariip0.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
