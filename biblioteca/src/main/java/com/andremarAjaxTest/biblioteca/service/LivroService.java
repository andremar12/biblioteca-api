package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.LivroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.LivroResponse;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    List<LivroResponse> findAll();
    Optional<LivroResponse> findById(Long id);
    LivroResponse create(LivroRequest request);
    LivroResponse update(Long id, LivroRequest request);
    void delete(Long id);
}
