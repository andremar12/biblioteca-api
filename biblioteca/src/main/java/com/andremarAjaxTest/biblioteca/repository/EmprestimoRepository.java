package com.andremarAjaxTest.biblioteca.repository;

import com.andremarAjaxTest.biblioteca.entity.Aluno;
import com.andremarAjaxTest.biblioteca.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDevolvidoFalseAndDataPrevistaDevolucaoBefore(LocalDate hoje);
    boolean existsByLivroId(Long livroId);
}
