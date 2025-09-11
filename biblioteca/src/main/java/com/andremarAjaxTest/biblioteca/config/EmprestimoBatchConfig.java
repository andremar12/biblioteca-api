package com.andremarAjaxTest.biblioteca.config;

import com.andremarAjaxTest.biblioteca.entity.Emprestimo;
import com.andremarAjaxTest.biblioteca.enums.StatusEmprestimo;
import com.andremarAjaxTest.biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class EmprestimoBatchConfig {

    private final EmprestimoRepository emprestimoRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job gerarRelatorioEmprestimosJob(Step emprestimoStep) {
        return new JobBuilder("gerarRelatorioEmprestimosJob", jobRepository)
                .start(emprestimoStep)
                .build();
    }

    @Bean
    public Step emprestimoStep(ItemReader<Emprestimo> reader,
                               ItemProcessor<Emprestimo, List<String>> processor,
                               ItemWriter<List<String>> writer) {
        return new StepBuilder("emprestimoStep", jobRepository)
                .<Emprestimo, List<String>>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Emprestimo> reader() {
        return new ItemReader<>() {
            private final Iterator<Emprestimo> iterator = emprestimoRepository.findAll().iterator();

            @Override
            public Emprestimo read() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }

    @Bean
    public ItemProcessor<Emprestimo, List<String>> processor() {
        return emprestimo -> {
            if (!emprestimo.getDevolvido() &&
                    emprestimo.getDataPrevistaDevolucao().isBefore(LocalDate.now())) {
                emprestimo.setStatus(StatusEmprestimo.ATRASADO);
            } else {
                emprestimo.setStatus(StatusEmprestimo.EM_DIA);
            }
            emprestimoRepository.save(emprestimo);

            return List.of(
                    emprestimo.getAluno().getNome(),
                    emprestimo.getAluno().getMatricula(),
                    emprestimo.getLivro().getTitulo(),
                    emprestimo.getDataEmprestimo().toString(),
                    emprestimo.getDataPrevistaDevolucao().toString(),
                    emprestimo.getDataDevolucao() != null ? emprestimo.getDataDevolucao().toString() : "",
                    emprestimo.getStatus().name()
            );
        };
    }

    @Bean
    public ItemWriter<List<String>> writer() {
        return items -> {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Empréstimos");

            String[] colunas = {"Aluno", "Matrícula", "Livro", "Data Empréstimo", "Data Prevista", "Data Devolução", "Status"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            int rowNum = 1;
            for (List<String> item : items) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < item.size(); i++) {
                    row.createCell(i).setCellValue(item.get(i));
                }
            }

            // Gera nome do arquivo com data atual
            String nomeArquivo = "relatorio_emprestimos_" + java.time.LocalDate.now() + ".xlsx";

            try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

    }
}
