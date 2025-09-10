package com.andremarAjaxTest.biblioteca.mapper;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import com.andremarAjaxTest.biblioteca.entity.Genero;

public class GeneroMapper {

    public static GeneroResponse toResponse(Genero genero) {
        if (genero == null) return null;
        return new GeneroResponse(
                genero.getId(),
                genero.getNome(),
                genero.getDescricao(),
                genero.getAtivo()
        );
    }

    public static Genero toEntity(GeneroRequest request) {
        if (request == null) return null;
        return new Genero(
                null,
                request.nome(),
                request.descricao(),
                request.ativo()
        );
    }
}
