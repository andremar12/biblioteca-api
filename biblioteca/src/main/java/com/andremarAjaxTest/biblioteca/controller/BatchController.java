package com.andremarAjaxTest.biblioteca.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job gerarRelatorioEmprestimosJob;

    @PostMapping("/relatorio-emprestimos")
    public ResponseEntity<String> executarRelatorioEmprestimos() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(gerarRelatorioEmprestimosJob, params);

            return ResponseEntity
                    .ok()
                    .header("X-Message", "Job de relatório de empréstimos iniciado com sucesso!")
                    .body("Job de relatório de empréstimos iniciado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .header("X-Message", "Erro ao iniciar o job: " + e.getMessage())
                    .body("Erro ao iniciar o job: " + e.getMessage());
        }
    }
}

