package com.andremarAjaxTest.biblioteca.service.impl;
import com.andremarAjaxTest.biblioteca.entity.Emprestimo;
import com.andremarAjaxTest.biblioteca.enums.StatusEmprestimo;
import com.andremarAjaxTest.biblioteca.repository.EmprestimoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioImprestimoImpl {
    private final EmprestimoRepository emprestimoRepository;

    public ByteArrayResource gerarRelatorioEmprestimos() {
        try {
            // 1️⃣ Atualiza status dos empréstimos
            List<Emprestimo> emprestimos = emprestimoRepository.findAll();
            for (Emprestimo e : emprestimos) {
                if (!e.getDevolvido() && e.getDataPrevistaDevolucao().isBefore(LocalDate.now())) {
                    e.setStatus(StatusEmprestimo.ATRASADO);
                } else {
                    e.setStatus(StatusEmprestimo.EM_DIA);
                }
                emprestimoRepository.save(e);
            }

            // 2️⃣ Cria o Excel em memória
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Empréstimos");

            // Cabeçalho
            String[] colunas = {"Aluno", "Matrícula", "Livro", "Data Empréstimo", "Data Prevista", "Data Devolução", "Status"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            // Conteúdo
            int rowNum = 1;
            for (Emprestimo e : emprestimos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getAluno().getNome());
                row.createCell(1).setCellValue(e.getAluno().getMatricula());
                row.createCell(2).setCellValue(e.getLivro().getTitulo());
                row.createCell(3).setCellValue(e.getDataEmprestimo().toString());
                row.createCell(4).setCellValue(e.getDataPrevistaDevolucao().toString());
                row.createCell(5).setCellValue(e.getDataDevolucao() != null ? e.getDataDevolucao().toString() : "");
                row.createCell(6).setCellValue(e.getStatus().name());
            }

            // Escreve em memória
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            return new ByteArrayResource(out.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório de empréstimos");
        }
    }
}
