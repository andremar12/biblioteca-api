package com.andremarAjaxTest.biblioteca.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmprestimoRequest(

        @NotNull(message = "Aluno é obrigatório")
        Long alunoId,

        @NotNull(message = "Livro é obrigatório")
        Long livroId,

        @NotNull(message = "Data de empréstimo é obrigatória")
        LocalDate dataEmprestimo,

        LocalDate dataDevolucao,

        Boolean devolvido
) {}
