package com.andremarAjaxTest.biblioteca.dto.response;

public record GeneroResponse(
        Long id,
        String nome,
        String descricao,
        Boolean ativo
) {}
