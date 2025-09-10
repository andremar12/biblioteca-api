package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.dto.request.EmprestimoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.EmprestimoResponse;
import com.andremarAjaxTest.biblioteca.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<EmprestimoResponse>> getAll() {
        return ResponseEntity.ok(emprestimoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponse> create(@RequestBody EmprestimoRequest request) {
        EmprestimoResponse emprestimo = emprestimoService.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/emprestimos/" + emprestimo.id()))
                .body(emprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponse> update(
            @PathVariable Long id,
            @RequestBody EmprestimoRequest request
    ) {
        return ResponseEntity.ok(emprestimoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
