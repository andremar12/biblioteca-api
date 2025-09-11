package com.andremarAjaxTest.biblioteca.dto.request;

import com.andremarAjaxTest.biblioteca.enums.StatusEmprestimo;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmprestimoRequest(
        @NotNull(message = "Aluno é obrigatório")
        Long alunoId,

        @NotNull(message = "Livro é obrigatório")
        Long livroId,

        @NotNull(message = "Data de empréstimo é obrigatória")
        LocalDate dataEmprestimo,

        @NotNull(message = "Data prevista de devolução é obrigatória")
        LocalDate dataPrevistaDevolucao,

        LocalDate dataDevolucao,

        Boolean devolvido,

        StatusEmprestimo status
) {}
