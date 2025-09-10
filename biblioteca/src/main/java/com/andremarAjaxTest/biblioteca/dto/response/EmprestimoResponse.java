package com.andremarAjaxTest.biblioteca.dto.response;

import java.time.LocalDate;

public record EmprestimoResponse(
        Long id,
        Long alunoId,
        String alunoNome,
        Long livroId,
        String livroTitulo,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        Boolean devolvido
) {}
