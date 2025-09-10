package com.andremarAjaxTest.biblioteca.mapper;

import com.andremarAjaxTest.biblioteca.dto.request.AlunoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AlunoResponse;
import com.andremarAjaxTest.biblioteca.entity.Aluno;

public class AlunoMapper {

    public static AlunoResponse toResponse(Aluno aluno) {
        if (aluno == null) return null;
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula()
        );
    }

    public static Aluno toEntity(AlunoRequest request) {
        if (request == null) return null;
        return Aluno.builder()
                .id(null)
                .nome(request.nome())
                .email(request.email())
                .matricula(request.matricula())
                .build();
    }
}
