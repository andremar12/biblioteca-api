package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.AutorRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;

import java.util.List;
import java.util.Optional;

public interface AutorService {
    List<AutorResponse> findAll();
    Optional<AutorResponse> findById(Long id);
    AutorResponse create(AutorRequest request);
    AutorResponse update(Long id, AutorRequest request);
    void delete(Long id);
}