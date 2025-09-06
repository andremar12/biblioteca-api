package com.andremarAjaxTest.biblioteca.service.impl;

import com.andremarAjaxTest.biblioteca.dto.request.GeneroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.GeneroResponse;
import com.andremarAjaxTest.biblioteca.entity.Genero;
import com.andremarAjaxTest.biblioteca.repository.GeneroRepository;
import com.andremarAjaxTest.biblioteca.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {
    private final GeneroRepository generoRepository;

    @Override
    public List<GeneroResponse> findAll() {
        return generoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Optional<GeneroResponse> findById(Long id) {
        return generoRepository.findById(id).map(this::toResponse);
    }

    @Override
    public GeneroResponse create(GeneroRequest request) {
        if (generoRepository.existsByNomeIgnoreCase(request.nome())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Já existe um gênero com esse nome."
            );
        }
        Genero genero = toEntity(request);
        return toResponse(generoRepository.save(genero));
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
        return toResponse(generoRepository.save(genero));
    }

    @Override
    public void delete(Long id) {
        if (!generoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Gênero não encontrado."
            );
        }
        generoRepository.deleteById(id);
    }

    private GeneroResponse toResponse(Genero genero) {
        return new GeneroResponse(
                genero.getId(),
                genero.getNome(),
                genero.getDescricao(),
                genero.getAtivo()
        );
    }

    private Genero toEntity(GeneroRequest request) {
        return Genero.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .ativo(request.ativo() != null ? request.ativo() : true)
                .build();
    }
}
