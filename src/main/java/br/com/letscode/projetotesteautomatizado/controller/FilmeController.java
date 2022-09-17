package br.com.letscode.projetotesteautomatizado.controller;

import br.com.letscode.projetotesteautomatizado.model.Filme;
import br.com.letscode.projetotesteautomatizado.service.FilmeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/filmes")
@AllArgsConstructor
public class FilmeController {

    private FilmeService filmeService;
    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody @Valid Filme filme){
        Filme filmeCadastrado = filmeService.adicionarFilme(filme);
        return new ResponseEntity<>(filmeCadastrado, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> acessarFilmes(){
        List<Filme> filmesCadastrados = filmeService.listarFilmes();

        return new ResponseEntity<>(filmesCadastrados, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> procurarFilmePorId(@PathVariable Long id){
        Filme filmeBuscado = filmeService.buscarFilmePorId(id);

        return new ResponseEntity<>(filmeBuscado,OK);
    }

    @PutMapping
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Filme novoFilme){
        Filme filme = filmeService.updateFilme(novoFilme);
        return new ResponseEntity<>(filme, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Filme> removerFilme(@PathVariable Long id){
        Filme filme = filmeService.removerFilme(id);
        return new ResponseEntity<>(filme, OK);
    }

}
