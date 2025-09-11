package com.andremarAjaxTest.biblioteca.dto.response;

import com.andremarAjaxTest.biblioteca.enums.StatusEmprestimo;

import java.time.LocalDate;

public record EmprestimoResponse(
        Long id,
        Long alunoId,
        String alunoNome,
        Long livroId,
        String livroTitulo,
        LocalDate dataEmprestimo,
        LocalDate dataPrevistaDevolucao,
        LocalDate dataDevolucao,
        Boolean devolvido,
        StatusEmprestimo status
) {}