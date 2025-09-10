package com.andremarAjaxTest.biblioteca.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlunoRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome pode ter no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail deve ser válido")
        @Size(max = 150, message = "E-mail pode ter no máximo 150 caracteres")
        String email,

        @NotBlank(message = "Matrícula é obrigatória")
        @Size(max = 20, message = "Matrícula pode ter no máximo 20 caracteres")
        String matricula
) {}
