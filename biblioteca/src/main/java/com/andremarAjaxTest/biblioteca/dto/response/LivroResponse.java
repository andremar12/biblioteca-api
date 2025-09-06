package com.andremarAjaxTest.biblioteca.dto.response;

public record LivroResponse(
        Long id,
        String titulo,
        AutorResponse autor,
        GeneroResponse genero
) {}