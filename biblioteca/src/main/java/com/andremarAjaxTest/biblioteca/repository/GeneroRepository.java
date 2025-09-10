package com.andremarAjaxTest.biblioteca.repository;


import com.andremarAjaxTest.biblioteca.entity.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    boolean existsByNomeIgnoreCase(String nome);

    Page<Genero> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}