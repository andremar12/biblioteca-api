package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.AlunoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AlunoResponse;
import com.andremarAjaxTest.biblioteca.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getAll() {
        return ResponseEntity.ok(alunoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@Valid @RequestBody AlunoRequest request) {
        AlunoResponse aluno = alunoService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/alunos/" + aluno.id()))
                .body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(
            @PathVariable Long id,
            @Valid
            @RequestBody AlunoRequest request
    ) {
        return ResponseEntity.ok(alunoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
