package com.andremarAjaxTest.biblioteca.service.impl;

import com.andremarAjaxTest.biblioteca.dto.request.AutorRequest;
import com.andremarAjaxTest.biblioteca.dto.response.AutorResponse;
import com.andremarAjaxTest.biblioteca.entity.Autor;
import com.andremarAjaxTest.biblioteca.mapper.AutorMapper;
import com.andremarAjaxTest.biblioteca.repository.AutorRepository;
import com.andremarAjaxTest.biblioteca.repository.LivroRepository;
import com.andremarAjaxTest.biblioteca.service.AutorService;
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
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private  final LivroRepository livroRepository;
    @Override
    public List<AutorResponse> findAll() {
        return autorRepository.findAll()
                .stream()
                .map(AutorMapper::toResponse)
                .toList();
    }

    public Page<AutorResponse> findAllPageable(String nome, Pageable pageable) {
        Page<Autor> page;
        if (nome != null && !nome.isBlank()) {
            page = autorRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            page = autorRepository.findAll(pageable);
        }
        return page.map(AutorMapper::toResponse);
    }

    @Override
    public Optional<AutorResponse> findById(Long id) {
        return autorRepository.findById(id).map(AutorMapper::toResponse);
    }

    @Override
    public AutorResponse create(AutorRequest request) {
        Autor autor = AutorMapper.toEntity(request);
        return AutorMapper.toResponse(autorRepository.save(autor));
    }

    @Override
    public AutorResponse update(Long id, AutorRequest request) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado."
                ));

        autor.setNome(request.nome());
        autor.setNacionalidade(request.nacionalidade());
        autor.setDataNascimento(request.dataNascimento());
        autor.setBiografia(request.biografia());

        return AutorMapper.toResponse(autorRepository.save(autor));
    }

    @Override
    public void delete(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Autor não encontrado."
            );
        }
        boolean existsBy = livroRepository.existsByAutorId(id);
        if(existsBy){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Autor vinculado a livro"
            );
        }
        autorRepository.deleteById(id);
    }
}
