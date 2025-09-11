package com.andremarAjaxTest.biblioteca.controller;

import com.andremarAjaxTest.biblioteca.service.impl.EmprestimoServiceImpl;
import com.andremarAjaxTest.biblioteca.service.impl.RelatorioImprestimoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/batch")
@RequiredArgsConstructor
public class BatchController {


    private final RelatorioImprestimoImpl relatorioImprestimo;

    @PostMapping("/relatorio-emprestimos")
    public ResponseEntity<ByteArrayResource> baixarRelatorioEmprestimos() {
        try {
            ByteArrayResource resource = relatorioImprestimo.gerarRelatorioEmprestimos();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_emprestimos.xlsx")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
