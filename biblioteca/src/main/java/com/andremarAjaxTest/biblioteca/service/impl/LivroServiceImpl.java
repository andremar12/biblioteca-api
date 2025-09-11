package com.andremarAjaxTest.biblioteca.service.impl;

import com.andremarAjaxTest.biblioteca.dto.request.LivroRequest;
import com.andremarAjaxTest.biblioteca.dto.response.LivroResponse;
import com.andremarAjaxTest.biblioteca.entity.Autor;
import com.andremarAjaxTest.biblioteca.entity.Genero;
import com.andremarAjaxTest.biblioteca.entity.Livro;
import com.andremarAjaxTest.biblioteca.mapper.LivroMapper;
import com.andremarAjaxTest.biblioteca.repository.AutorRepository;
import com.andremarAjaxTest.biblioteca.repository.EmprestimoRepository;
import com.andremarAjaxTest.biblioteca.repository.GeneroRepository;
import com.andremarAjaxTest.biblioteca.repository.LivroRepository;
import com.andremarAjaxTest.biblioteca.service.LivroService;
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
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;
    private final EmprestimoRepository emprestimoRepository;

    @Override
    public List<LivroResponse> findAll() {
        return livroRepository.findAll()
                .stream()
                .map(LivroMapper::toResponse)
                .toList();
    }

    public Page<LivroResponse> findAllPageable(String titulo, Long autorId, Pageable pageable) {
        String filtroTitulo = titulo != null ? titulo : "";
        Page<Livro> page = livroRepository.findByTituloAndAutorId(filtroTitulo, autorId, pageable);
        return page.map(LivroMapper::toResponse);
    }

    @Override
    public Optional<LivroResponse> findById(Long id) {
        return livroRepository.findById(id).map(LivroMapper::toResponse);
    }

    @Override
    public LivroResponse create(LivroRequest request) {
        Autor autor = autorRepository.findById(request.autorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado."
                ));
        Genero genero = generoRepository.findById(request.generoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gênero não encontrado."
                ));

        Livro livro = Livro.builder()
                .titulo(request.titulo())
                .autor(autor)
                .genero(genero)
                .build();

        return LivroMapper.toResponse(livroRepository.save(livro));
    }

    @Override
    public LivroResponse update(Long id, LivroRequest request) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Livro não encontrado."
                ));

        Autor autor = autorRepository.findById(request.autorId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado."
                ));
        Genero genero = generoRepository.findById(request.generoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Gênero não encontrado."
                ));

        livro.setTitulo(request.titulo());
        livro.setAutor(autor);
        livro.setGenero(genero);

        return LivroMapper.toResponse(livroRepository.save(livro));
    }

    @Override
    public void delete(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Livro não encontrado."
            );
        }
        boolean existsBy = emprestimoRepository.existsByLivroId(id);
        if(existsBy){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Livro vinculado a emprestimo"
            );
        }
        livroRepository.deleteById(id);
    }
}
