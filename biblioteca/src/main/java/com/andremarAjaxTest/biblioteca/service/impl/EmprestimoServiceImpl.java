package com.andremarAjaxTest.biblioteca.service.impl;

import com.andremarAjaxTest.biblioteca.dto.request.EmprestimoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.EmprestimoResponse;
import com.andremarAjaxTest.biblioteca.entity.Aluno;
import com.andremarAjaxTest.biblioteca.entity.Emprestimo;
import com.andremarAjaxTest.biblioteca.entity.Livro;
import com.andremarAjaxTest.biblioteca.mapper.EmprestimoMapper;
import com.andremarAjaxTest.biblioteca.repository.AlunoRepository;
import com.andremarAjaxTest.biblioteca.repository.EmprestimoRepository;
import com.andremarAjaxTest.biblioteca.repository.LivroRepository;
import com.andremarAjaxTest.biblioteca.service.EmprestimoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;

    @Override
    public List<EmprestimoResponse> getAll() {
        return emprestimoRepository.findAll()
                .stream()
                .map(EmprestimoMapper::toResponse)
                .toList();
    }

    @Override
    public EmprestimoResponse create(EmprestimoRequest request) {
        Aluno aluno = alunoRepository.findById(request.alunoId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + request.alunoId()));

        Livro livro = livroRepository.findById(request.livroId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com ID: " + request.livroId()));

        Emprestimo emprestimo = EmprestimoMapper.toEntity(request, aluno, livro);

        emprestimo = emprestimoRepository.save(emprestimo);
        return EmprestimoMapper.toResponse(emprestimo);
    }

    @Override
    public EmprestimoResponse update(Long id, EmprestimoRequest request) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado com ID: " + id));

        Aluno aluno = alunoRepository.findById(request.alunoId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + request.alunoId()));

        Livro livro = livroRepository.findById(request.livroId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com ID: " + request.livroId()));

        emprestimo.setAluno(aluno);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(request.dataEmprestimo());
        emprestimo.setDataDevolucao(request.dataDevolucao());

        emprestimo = emprestimoRepository.save(emprestimo);
        return EmprestimoMapper.toResponse(emprestimo);
    }

    @Override
    public void delete(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new EntityNotFoundException("Empréstimo não encontrado com ID: " + id);
        }
        emprestimoRepository.deleteById(id);
    }

    @Override
    public EmprestimoResponse getById(Long id) {
        return emprestimoRepository.findById(id)
                .map(EmprestimoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado com ID: " + id));
    }

}
