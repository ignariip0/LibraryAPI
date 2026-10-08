package com.github.ignariip0.libraryapi.service;

import com.github.ignariip0.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.ignariip0.libraryapi.model.GeneroLivro;
import com.github.ignariip0.libraryapi.model.Livro;
import com.github.ignariip0.libraryapi.repository.LivroRepository;
import com.github.ignariip0.libraryapi.repository.specs.LivroSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static com.github.ignariip0.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;


    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public List<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao){


        // select * from livro where 0 = 0
        Specification<Livro> specs = Specification.where((root, query, cb) ->
                cb.conjunction());

        if (isbn != null){
            //query = query and isbn = :isbn
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }


        if (titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if (genero != null){
            specs = specs.and(generoEqual(genero));
        }

        return repository.findAll(specs);

    }

}