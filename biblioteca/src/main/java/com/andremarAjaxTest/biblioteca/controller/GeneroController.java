package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import com.andremarAjaxTest.biblioteca.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<GeneroResponse>> getAll() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<GeneroResponse>> getAllPageable(
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        return ResponseEntity.ok(generoService.findAllPageable(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponse> getById(@PathVariable Long id) {
        return generoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroResponse> create(@Valid @RequestBody GeneroRequest request) {
        return ResponseEntity.ok(generoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponse> update(@PathVariable Long id, @Valid @RequestBody GeneroRequest request) {
        return ResponseEntity.ok(generoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        generoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}