package com.andremarAjaxTest.biblioteca.service.impl;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import com.andremarAjaxTest.biblioteca.dto.response.LivroResponse;
import com.andremarAjaxTest.biblioteca.entity.Genero;
import com.andremarAjaxTest.biblioteca.entity.Livro;
import com.andremarAjaxTest.biblioteca.mapper.GeneroMapper;
import com.andremarAjaxTest.biblioteca.repository.GeneroRepository;
import com.andremarAjaxTest.biblioteca.repository.LivroRepository;
import com.andremarAjaxTest.biblioteca.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {
    private final GeneroRepository generoRepository;
    private final LivroRepository livroRepository;
    @Override
    public List<GeneroResponse> findAll() {
        return generoRepository.findAll()
                .stream()
                .map(GeneroMapper::toResponse)
                .toList();
    }

    public Page<GeneroResponse> findAllPageable(String nome, Pageable pageable) {
        Page<Genero> page;
        if (nome != null && !nome.isBlank()) {
            page = generoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            page = generoRepository.findAll(pageable);
        }
        return page.map(GeneroMapper::toResponse);
    }

    @Override
    public Optional<GeneroResponse> findById(Long id) {
        return generoRepository.findById(id).map(GeneroMapper::toResponse);
    }

    @Override
    public GeneroResponse create(GeneroRequest request) {
        if (generoRepository.existsByNomeIgnoreCase(request.nome())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Já existe um gênero com esse nome."
            );
        }
        Genero genero = GeneroMapper.toEntity(request);
        return GeneroMapper.toResponse(generoRepository.save(genero));
    }

    @Override
    public GeneroResponse update(Long id, GeneroRequest request) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gênero não encontrado."
                ));
        genero.setNome(request.nome());
        genero.setDescricao(request.descricao());
        genero.setAtivo(request.ativo() != null ? request.ativo() : true);

        return GeneroMapper.toResponse(generoRepository.save(genero));
    }

    @Override
    public void delete(Long id) {
        if (!generoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Gênero não encontrado."
            );
        }
        boolean existsBy = livroRepository.existsByGeneroId(id);
        if(existsBy){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Gênero com livros vinculado"
            );
        }
        generoRepository.deleteById(id);
    }
}
