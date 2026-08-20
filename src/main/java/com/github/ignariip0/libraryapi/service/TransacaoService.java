package com.github.ignariip0.libraryapi.service;

import com.github.ignariip0.libraryapi.model.Autor;
import com.github.ignariip0.libraryapi.model.GeneroLivro;
import com.github.ignariip0.libraryapi.model.Livro;
import com.github.ignariip0.libraryapi.repository.AutorRepository;
import com.github.ignariip0.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("0196c3d4-bf22-482a-8fa7-f512a7a329e9"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2025,6,1));
    }

    @Transactional
    public void executar(){
        // Salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2007, 7, 20));

        autorRepository.save(autor);


        // Salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback");
        }
    }


}