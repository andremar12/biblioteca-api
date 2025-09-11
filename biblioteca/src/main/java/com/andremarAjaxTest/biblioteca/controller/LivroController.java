package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.LivroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.LivroResponse;
import com.andremarAjaxTest.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponse>> getAllPageable(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long autorId,
            Pageable pageable
    ) {
        Page<LivroResponse> page = livroService.findAllPageable(titulo, autorId, pageable);
        return ResponseEntity.ok()
                .header("X-Message", "Livros carregados com sucesso!")
                .body(page);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LivroResponse>> getAll() {
        List<LivroResponse> livros = livroService.findAll();
        return ResponseEntity.ok()
                .header("X-Message", "Todos os livros carregados com sucesso!")
                .body(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> getById(@PathVariable Long id) {
        return livroService.findById(id)
                .map(livro -> ResponseEntity.ok()
                        .header("X-Message", "Livro encontrado com sucesso!")
                        .body(livro))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LivroResponse> create(@Valid @RequestBody LivroRequest request) {
        LivroResponse livro = livroService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/livros/" + livro.id()))
                .header("X-Message", "Livro criado com sucesso!")
                .body(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> update(@PathVariable Long id, @Valid @RequestBody LivroRequest request) {
        LivroResponse livro = livroService.update(id, request);
        return ResponseEntity.ok()
                .header("X-Message", "Livro atualizado com sucesso!")
                .body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent()
                .header("X-Message", "Livro deletado com sucesso!")
                .build();
    }
}
