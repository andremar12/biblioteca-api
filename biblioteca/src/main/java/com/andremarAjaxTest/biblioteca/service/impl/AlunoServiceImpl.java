package com.andremarAjaxTest.biblioteca.service.impl;

import com.andremarAjaxTest.biblioteca.dto.request.AlunoRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AlunoResponse;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import com.andremarAjaxTest.biblioteca.entity.Aluno;
import com.andremarAjaxTest.biblioteca.entity.Autor;
import com.andremarAjaxTest.biblioteca.mapper.AlunoMapper;
import com.andremarAjaxTest.biblioteca.mapper.AutorMapper;
import com.andremarAjaxTest.biblioteca.repository.AlunoRepository;
import com.andremarAjaxTest.biblioteca.service.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    @Override
    public List<AlunoResponse> getAll() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoMapper::toResponse)
                .toList();
    }

    public Page<AlunoResponse> findAllPageable(String nome, Pageable pageable) {
        Page<Aluno> page;
        if (nome != null && !nome.isBlank()) {
            page = alunoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            page = alunoRepository.findAll(pageable);
        }
        return page.map(AlunoMapper::toResponse);
    }
    @Override
    public AlunoResponse create(AlunoRequest request) {
        Aluno aluno = AlunoMapper.toEntity(request);
        aluno = alunoRepository.save(aluno);
        return AlunoMapper.toResponse(aluno);
    }

    @Override
    public AlunoResponse update(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        aluno.setNome(request.nome());
        aluno.setEmail(request.email());
        aluno.setMatricula(request.matricula());

        aluno = alunoRepository.save(aluno);
        return AlunoMapper.toResponse(aluno);
    }

    @Override
    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + id);
        }
        alunoRepository.deleteById(id);
    }

    @Override
    public AlunoResponse getById(Long id) {
        return alunoRepository.findById(id)
                .map(AlunoMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));
    }

}
