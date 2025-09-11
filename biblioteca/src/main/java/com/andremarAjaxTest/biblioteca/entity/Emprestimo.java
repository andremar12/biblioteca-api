package com.andremarAjaxTest.biblioteca.entity;

import com.andremarAjaxTest.biblioteca.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private Boolean devolvido;

    @Column(name = "data_prevista_devolucao", nullable = false)
    private LocalDate dataPrevistaDevolucao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;
}
