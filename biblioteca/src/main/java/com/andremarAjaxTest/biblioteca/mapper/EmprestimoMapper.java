package com.andremarAjaxTest.biblioteca.mapper;

import com.andremarAjaxTest.biblioteca.dto.request.EmprestimoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.EmprestimoResponse;
import com.andremarAjaxTest.biblioteca.entity.Aluno;
import com.andremarAjaxTest.biblioteca.entity.Emprestimo;
import com.andremarAjaxTest.biblioteca.entity.Livro;

public class EmprestimoMapper {

    public static EmprestimoResponse toResponse(Emprestimo emprestimo) {
        if (emprestimo == null) return null;
        return new EmprestimoResponse(
                emprestimo.getId(),
                emprestimo.getAluno() != null ? emprestimo.getAluno().getId() : null,
                emprestimo.getAluno() != null ? emprestimo.getAluno().getNome() : null,
                emprestimo.getLivro() != null ? emprestimo.getLivro().getId() : null,
                emprestimo.getLivro() != null ? emprestimo.getLivro().getTitulo() : null,
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getDevolvido()
        );
    }

    public static Emprestimo toEntity(EmprestimoRequest request, Aluno aluno, Livro livro) {
        if (request == null) return null;
        return new Emprestimo(
                null,
                aluno,
                livro,
                request.dataEmprestimo(),
                request.dataDevolucao(),
                request.devolvido() != null ? request.devolvido() : false
        );
    }
}
