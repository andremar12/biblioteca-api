package com.andremarAjaxTest.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AutorRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome pode ter no máximo 150 caracteres")
        String nome,

        @Size(max = 200, message = "Nacionalidade pode ter no máximo 200 caracteres")
        String nacionalidade,

        LocalDate dataNascimento,

        @Size(max = 255, message = "Biografia pode ter no máximo 255 caracteres")
        String biografia
) {}
