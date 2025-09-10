package com.andremarAjaxTest.biblioteca.mapper;

import com.andremarAjaxTest.biblioteca.dto.request.LivroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.LivroResponse;
import com.andremarAjaxTest.biblioteca.entity.Livro;

public class LivroMapper {

    public static LivroResponse toResponse(Livro livro) {
        if (livro == null) return null;
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                AutorMapper.toResponse(livro.getAutor()),
                GeneroMapper.toResponse(livro.getGenero())
        );
    }

    public static Livro toEntity(LivroRequest request) {
        if (request == null) return null;
        return Livro.builder()
                .id(null)
                .titulo(request.titulo())
                .autor(null)
                .genero(null)
                .build();
    }
}
