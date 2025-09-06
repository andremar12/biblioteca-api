package com.andremarAjaxTest.biblioteca.repository;

import com.andremarAjaxTest.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
