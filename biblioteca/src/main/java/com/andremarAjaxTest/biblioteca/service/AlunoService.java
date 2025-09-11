package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.AlunoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AlunoResponse;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlunoService {
    List<AlunoResponse> getAll();
    Page<AlunoResponse> findAllPageable(String nome, Pageable pageable);
    AlunoResponse create(AlunoRequest request);
    AlunoResponse update(Long id, AlunoRequest request);
    void delete(Long id);
    AlunoResponse getById(Long id);
}
