package br.com.letscode.projetotesteautomatizado.controller;

import br.com.letscode.projetotesteautomatizado.model.Ator;
import br.com.letscode.projetotesteautomatizado.service.AtorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@RestController
@AllArgsConstructor
public class AtorController {

    private AtorService atorService;
    @PostMapping
    public ResponseEntity<Ator> cadastrarAtor(@RequestBody @Valid Ator ator){
        Ator atorCadastrado = atorService.adicionarAtor(ator);
        return new ResponseEntity<>(atorCadastrado, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Ator>> acessarAtores(){
        List<Ator> atoresCadastrados = atorService.listarAtores();

        return new ResponseEntity<>(atoresCadastrados, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> procurarAtorPorId(@PathVariable Long id){
        Ator atorBuscado = atorService.buscarAtorPorId(id);

        return new ResponseEntity<>(atorBuscado,OK);
    }

    @PutMapping
    public ResponseEntity<Ator> atualizarAtor(@PathVariable Ator novoAtor){
        Ator ator = atorService.updateAtor(novoAtor);
        return new ResponseEntity<>(ator, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ator> removerAtor(@PathVariable Long id){
        Ator ator = atorService.removerAtor(id);
        return new ResponseEntity<>(ator, OK);
    }
}
