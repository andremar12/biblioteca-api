package com.andremarAjaxTest.biblioteca.batchRoutine;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job gerarRelatorioEmprestimosJob;

    @Scheduled(cron = "0 59 1 * * *")
    public void executarRelatorioEmprestimos() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(gerarRelatorioEmprestimosJob, params);
            System.out.println("Job de relatório executado automaticamente! Arquivo diário gerado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
