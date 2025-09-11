package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.LivroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.LivroResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    List<LivroResponse> findAll();
    Page<LivroResponse> findAllPageable(String nome,Long autorId, Pageable pageable);
    Optional<LivroResponse> findById(Long id);
    LivroResponse create(LivroRequest request);
    LivroResponse update(Long id, LivroRequest request);
    void delete(Long id);
}
