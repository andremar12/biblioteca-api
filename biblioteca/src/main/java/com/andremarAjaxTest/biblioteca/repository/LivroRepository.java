package com.andremarAjaxTest.biblioteca.repository;

import com.andremarAjaxTest.biblioteca.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    boolean existsByGeneroId(Long generoId);
    boolean existsByAutorId(Long autorId);

    @Query(""" 
    SELECT l FROM Livro l
    WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
      AND (:autorId IS NULL OR l.autor.id = :autorId)
  """)
    Page<Livro> findByTituloAndAutorId(
            @Param("titulo") String titulo,
            @Param("autorId") Long autorId,
            Pageable pageable
    );

}
