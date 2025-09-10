package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.AutorRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    List<AutorResponse> findAll();
    Page<AutorResponse> findAllPageable(String nome, Pageable pageable);
    Optional<AutorResponse> findById(Long id);
    AutorResponse create(AutorRequest request);
    AutorResponse update(Long id, AutorRequest request);
    void delete(Long id);
}