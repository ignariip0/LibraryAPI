package com.github.ignariip0.libraryapi.controller.mappers;

import com.github.ignariip0.libraryapi.controller.dto.AutorDTO;
import com.github.ignariip0.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    // Exemplo do MapStruct
    @Mapping(source = "nome", target = "nome")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);

}