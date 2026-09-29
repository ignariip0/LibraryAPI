package com.github.ignariip0.libraryapi.service;

import com.github.ignariip0.libraryapi.model.Livro;
import com.github.ignariip0.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;


    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}