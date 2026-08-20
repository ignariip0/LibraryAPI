package com.github.ignariip0.libraryapi.repository;

import com.github.ignariip0.libraryapi.model.Autor;
import com.github.ignariip0.libraryapi.model.GeneroLivro;
import com.github.ignariip0.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;


    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Cauê");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2007, 7, 20));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("0dbe6db6-eb3d-4227-a959-d0afa074064f");

        Optional<Autor> possivelAutor = repository.findById(id);
        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do autos: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("0dbe6db6-eb3d-4227-a959-d0afa074064f");

        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("05ab09bc-1f13-42c3-b009-2a37f473b718");
        var caue = repository.findById(id).get();
        repository.delete(caue);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americana");
        autor.setDataNascimento(LocalDate.of(1998, 7, 20));

        Livro livro = new Livro();
        livro.setIsbn("18547-84874");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1960,1,2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("92727-84874");
        livro2.setPreco(BigDecimal.valueOf(104));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Arsene Lupin");
        livro2.setDataPublicacao(LocalDate.of(1950,1,2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
//        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("b9ba5fcb-d136-4fc7-8f57-d5ad3716d442");
        var autor = repository.findById(id).get();

        // Buscar os livros do autor
        List<Livro> livroLista = livroRepository.findByAutor(autor);
        autor.setLivros(livroLista);

        autor.getLivros().forEach(System.out::println);
    }

}