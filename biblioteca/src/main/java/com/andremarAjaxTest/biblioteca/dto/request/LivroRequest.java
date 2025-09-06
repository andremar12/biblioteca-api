package com.andremarAjaxTest.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroRequest(
        @NotBlank(message = "Título é obrigatório")
        @Size(max = 200, message = "Título pode ter no máximo 200 caracteres")
        String titulo,

        @NotNull(message = "Autor é obrigatório")
        Long autorId,

        @NotNull(message = "Gênero é obrigatório")
        Long generoId
) {}