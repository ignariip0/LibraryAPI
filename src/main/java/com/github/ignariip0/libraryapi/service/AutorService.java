package com.github.ignariip0.libraryapi.service;

import com.github.ignariip0.libraryapi.model.Autor;
import com.github.ignariip0.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {


    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }


}