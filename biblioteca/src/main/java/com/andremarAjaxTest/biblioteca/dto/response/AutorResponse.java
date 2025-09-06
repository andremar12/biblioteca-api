package com.andremarAjaxTest.biblioteca.dto.response;

import java.time.LocalDate;

public record AutorResponse(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento,
        String biografia
) {}