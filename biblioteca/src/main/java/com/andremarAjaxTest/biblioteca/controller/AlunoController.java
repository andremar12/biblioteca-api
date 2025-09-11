package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.AlunoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AlunoResponse;
import com.andremarAjaxTest.biblioteca.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping("all")
    public ResponseEntity<List<AlunoResponse>> getAll() {
        return ResponseEntity
                .ok()
                .header("X-Message", "Lista completa de alunos carregada com sucesso!")
                .body(alunoService.getAll());
    }
    @GetMapping
    public ResponseEntity<Page<AlunoResponse>> getAllPageable(
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        return ResponseEntity
                .ok()
                .header("X-Message", "Alunos paginados carregados com sucesso!")
                .body(alunoService.findAllPageable(nome, pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {
        AlunoResponse aluno = alunoService.getById(id);
        return ResponseEntity
                .ok()
                .header("X-Message", "Aluno encontrado com sucesso!")
                .body(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@Valid @RequestBody AlunoRequest request) {
        AlunoResponse aluno = alunoService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/alunos/" + aluno.id()))
                .header("X-Message", "Aluno criado com sucesso!")
                .body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AlunoRequest request
    ) {
        AlunoResponse aluno = alunoService.update(id, request);
        return ResponseEntity
                .ok()
                .header("X-Message", "Aluno atualizado com sucesso!")
                .body(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity
                .noContent()
                .header("X-Message", "Aluno excluído com sucesso!")
                .build();
    }
}
