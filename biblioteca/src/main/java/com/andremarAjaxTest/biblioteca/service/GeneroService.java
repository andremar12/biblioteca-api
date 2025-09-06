package com.andremarAjaxTest.biblioteca.service;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GeneroService {
    List<GeneroResponse> findAll();
    Optional<GeneroResponse> findById(Long id);
    GeneroResponse create(GeneroRequest request);
    GeneroResponse update(Long id, GeneroRequest request);
    void delete(Long id);
}