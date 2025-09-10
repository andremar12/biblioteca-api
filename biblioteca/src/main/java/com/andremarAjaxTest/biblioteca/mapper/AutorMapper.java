package com.andremarAjaxTest.biblioteca.mapper;

import com.andremarAjaxTest.biblioteca.dto.request.AutorRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import com.andremarAjaxTest.biblioteca.entity.Autor;

public class AutorMapper {

    public static AutorResponse toResponse(Autor autor) {
        if (autor == null) return null;
        return new AutorResponse(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento() != null ? autor.getDataNascimento() : null,
                autor.getBiografia()
        );
    }

    public static Autor toEntity(AutorRequest request) {
        if (request == null) return null;
        return new Autor(
                null,
                request.nome(),
                request.nacionalidade(),
                request.dataNascimento(),
                request.biografia()
        );
    }
}
