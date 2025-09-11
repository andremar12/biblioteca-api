package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import com.andremarAjaxTest.biblioteca.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        return ResponseEntity
                .ok()
                .header("X-Message", "Lista completa de gêneros carregada com sucesso!")
                .body(generoService.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<GeneroResponse>> getAllPageable(
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        return ResponseEntity
                .ok()
                .header("X-Message", "Gêneros paginados carregados com sucesso!")
                .body(generoService.findAllPageable(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponse> getById(@PathVariable Long id) {
        return generoService.findById(id)
                .map(genero -> ResponseEntity
                        .ok()
                        .header("X-Message", "Gênero encontrado com sucesso!")
                        .body(genero))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroResponse> create(@Valid @RequestBody GeneroRequest request) {
        GeneroResponse genero = generoService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/generos/" + genero.id()))
                .header("X-Message", "Gênero criado com sucesso!")
                .body(genero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponse> update(@PathVariable Long id, @Valid @RequestBody GeneroRequest request) {
        GeneroResponse genero = generoService.update(id, request);
        return ResponseEntity
                .ok()
                .header("X-Message", "Gênero atualizado com sucesso!")
                .body(genero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        generoService.delete(id);
        return ResponseEntity
                .noContent()
                .header("X-Message", "Gênero excluído com sucesso!")
                .build();
    }
}
