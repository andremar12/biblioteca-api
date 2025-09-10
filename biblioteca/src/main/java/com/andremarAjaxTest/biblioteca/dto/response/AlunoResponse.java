package com.andremarAjaxTest.biblioteca.dto.response;

public record AlunoResponse(
        Long id,
        String nome,
        String email,
        String matricula
) {}