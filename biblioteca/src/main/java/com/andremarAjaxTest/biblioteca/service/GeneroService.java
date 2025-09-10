package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface GeneroService {
    List<GeneroResponse> findAll();
    Page<GeneroResponse> findAllPageable(String nome, Pageable pageable);
    Optional<GeneroResponse> findById(Long id);
    GeneroResponse create(GeneroRequest request);
    GeneroResponse update(Long id, GeneroRequest request);
    void delete(Long id);
}