package com.andremarAjaxTest.biblioteca.repository;

import com.andremarAjaxTest.biblioteca.entity.Aluno;
import com.andremarAjaxTest.biblioteca.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
