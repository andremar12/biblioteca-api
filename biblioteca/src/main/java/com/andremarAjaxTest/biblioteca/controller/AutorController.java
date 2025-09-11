package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.AutorRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import com.andremarAjaxTest.biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AutorResponse>> getAll() {
        return ResponseEntity
                .ok()
                .header("X-Message", "Lista completa de autores carregada com sucesso!")
                .body(autorService.findAll());
    }

    @GetMapping
    public ResponseEntity<Page<AutorResponse>> getAllPageable(
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        return ResponseEntity
                .ok()
                .header("X-Message", "Autores paginados carregados com sucesso!")
                .body(autorService.findAllPageable(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> getById(@PathVariable Long id) {
        return autorService.findById(id)
                .map(autor -> ResponseEntity
                        .ok()
                        .header("X-Message", "Autor encontrado com sucesso!")
                        .body(autor))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AutorResponse> create(@Valid @RequestBody AutorRequest request) {
        AutorResponse autor = autorService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/autores/" + autor.id()))
                .header("X-Message", "Autor criado com sucesso!")
                .body(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> update(@PathVariable Long id, @Valid @RequestBody AutorRequest request) {
        AutorResponse autor = autorService.update(id, request);
        return ResponseEntity
                .ok()
                .header("X-Message", "Autor atualizado com sucesso!")
                .body(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity
                .noContent()
                .header("X-Message", "Autor excluído com sucesso!")
                .build();
    }
}
