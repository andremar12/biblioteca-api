package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.AlunoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AlunoResponse;

import java.util.List;

public interface AlunoService {
    List<AlunoResponse> getAll();
    AlunoResponse create(AlunoRequest request);
    AlunoResponse update(Long id, AlunoRequest request);
    void delete(Long id);
    AlunoResponse getById(Long id);
}
