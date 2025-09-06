package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.AutorRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import com.andremarAjaxTest.biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorResponse>> getAll() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> getById(@PathVariable Long id) {
        return autorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutorResponse> create(@Valid @RequestBody AutorRequest request) {
        return ResponseEntity.ok(autorService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> update(@PathVariable Long id, @Valid @RequestBody AutorRequest request) {
        return ResponseEntity.ok(autorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
