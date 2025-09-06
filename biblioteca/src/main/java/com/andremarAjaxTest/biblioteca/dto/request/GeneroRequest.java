package com.andremarAjaxTest.biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres")
        String nome,
        String descricao,
        Boolean ativo
) {}