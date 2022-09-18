package br.com.letscode.projetotesteautomatizado.service;

import br.com.letscode.projetotesteautomatizado.exception.AtorNotFoundException;
import br.com.letscode.projetotesteautomatizado.model.Ator;
import br.com.letscode.projetotesteautomatizado.repository.AtorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AtorServiceTest {
    @Autowired
    private AtorService atorService;
    @Autowired
    private AtorRepository atorRepository;

    @BeforeEach
    public void setUp(){
        atorRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve adicionar um ator no repositorio")
    void adicionarAtor() {
        Ator ator = Ator.builder().nome("Jackie Chan").build();
        var atorInserido = atorService.adicionarAtor(ator);

        Assertions.assertEquals(ator.getNome(), atorInserido.getNome());

    }

    @Test
    @DisplayName("Deve retornar um ator a partir de um Id")
    void buscarAtorPorId() {
        Ator ator = Ator.builder().nome("Jackie Chan").build();
        var atorInserido = atorService.adicionarAtor(ator);

        var atorBuscado = atorService.buscarAtorPorId(atorInserido.getId());

        Assertions.assertEquals(atorInserido.getNome(), atorBuscado.getNome());
    }

    @Test
    @DisplayName("Deve lançar uma exceção com id inválido")
    void buscarAtorPorIdInvalido(){
        var exception = Assertions.assertThrows(AtorNotFoundException.class,
                () -> atorService.buscarAtorPorId(7L));
        String message = "Ator não encontrado";

        Assertions.assertEquals(exception.getReason(), message);

    }

    @Test
    @DisplayName("Deve retornar uma lista de atores")
    void listarAtores() {
        Ator ator = Ator.builder().nome("Fernanda Montenegro").build();
        Ator ator2 = Ator.builder().nome("Fernanda Torres").build();
        Ator ator3 = Ator.builder().nome("Caio Blat").build();

        atorService.adicionarAtor(ator);
        atorService.adicionarAtor(ator2);
        atorService.adicionarAtor(ator3);

        var atores = atorService.listarAtores();

        Assertions.assertEquals(3, atores.size());
    }

    @Test
    @DisplayName("Deve retornar um ator a partir de um nome")
    void procurarAtorPorNome() {
        Ator ator = Ator.builder().nome("Fernanda Montenegro").build();
        atorService.adicionarAtor(ator);

        var atorBuscado = atorService.procurarAtorPorNome(ator.getNome());

        Assertions.assertEquals(ator.getNome(), atorBuscado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção com nome inválido")
    public void procurarAtorPorNomeInvalido(){
        Ator ator = Ator.builder().nome("Fernanda Montenegro").build();
        atorService.adicionarAtor(ator);

        var exception = Assertions.assertThrows(AtorNotFoundException.class,
                () -> atorService.procurarAtorPorNome("Renata Sorrah"));

        String message = "Ator não encontrado";

        Assertions.assertEquals(exception.getReason(), message);

    }

    @Test
    @DisplayName("Deve remover um ator do repositorio")
    void removerAtorTest() {
        Ator ator = Ator.builder().nome("Fernanda Montenegro").build();
        atorService.adicionarAtor(ator);

        atorService.removerAtor(ator.getId());
        var atores = atorService.listarAtores();

        Assertions.assertEquals(0, atores.size());
    }

    @Test
    @DisplayName("Deve atualizar o cadastro de um ator")
    void updateAtorTest() {
        Ator ator = Ator.builder().nome("Jackie Chan").build();
        Ator atorInserido = atorService.adicionarAtor(ator);


        var novoAtor = Ator.builder().id(atorInserido.getId()).nome("Debora Falabella").build();
        atorService.updateAtor(novoAtor);
        var atorAtualizado = atorService.buscarAtorPorId(1L);
        Assertions.assertEquals("Debora Falabella", atorAtualizado.getNome());
    }
}