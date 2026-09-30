package com.github.ignariip0.libraryapi.controller;

import com.github.ignariip0.libraryapi.controller.dto.AutorDTO;
import com.github.ignariip0.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ignariip0.libraryapi.controller.dto.ErroResposta;
import com.github.ignariip0.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.ignariip0.libraryapi.controller.mappers.LivroMapper;
import com.github.ignariip0.libraryapi.model.Livro;
import com.github.ignariip0.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id){
        var idLivro = UUID.fromString(id);

        return service
                .obterPorId(idLivro)
                .map(livro -> {
                    ResultadoPesquisaLivroDTO dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}