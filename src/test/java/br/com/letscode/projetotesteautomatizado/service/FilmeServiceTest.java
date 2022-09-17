package br.com.letscode.projetotesteautomatizado.service;

import br.com.letscode.projetotesteautomatizado.exception.FilmeNotFoundException;
import br.com.letscode.projetotesteautomatizado.model.Ator;
import br.com.letscode.projetotesteautomatizado.model.Filme;
import br.com.letscode.projetotesteautomatizado.repository.AtorRepository;
import br.com.letscode.projetotesteautomatizado.repository.FilmeRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FilmeServiceTest {

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private AtorRepository atorRepository;
    @Autowired
    private FilmeService filmeService;

    @BeforeEach
    public void limpaBanco(){
        filmeRepository.deleteAll();
        atorRepository.deleteAll();
    }

    @Test
    @DisplayName("deve adicionar um filme no repositorio")
    public void adicionarFilmeNoRepositorioTest() {
        Ator ator1 = atorRepository.save(Ator.builder().nome("Scarlett Johansson").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Morgan Freeman").build());

        Filme filme = Filme.builder()
                .nome("Lucy")
                .genero("Ficção Científica")
                .ano(2014)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.adicionarFilme(filme);

        List<Filme> filmes = filmeService.listarFilmes();

        Assertions.assertEquals(1, filmes.size());
        Assertions.assertEquals("Lucy", filmes.get(0).getNome());
    }

    @Test
    @DisplayName("Deve retornar um filme a partir do nome")
    public void procurarFilmePorNomeTest() {
        Ator ator1 = atorRepository.save(Ator.builder().nome("Scarlett Johansson").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Morgan Freeman").build());

        Filme filme = Filme.builder()
                .nome("Lucy")
                .genero("Ficção Científica")
                .ano(2014)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.adicionarFilme(filme);

        var filmeBuscado = filmeService.procurarFilmePorNome("Lucy");

        Assertions.assertEquals("Lucy",filmeBuscado.getNome());
    }

    @Test
    @DisplayName("Deve remover um filme do repositório")
    public void removerFilmeTest(){
        Ator ator1 = atorRepository.save(Ator.builder().nome("Scarlett Johansson").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Morgan Freeman").build());

        Filme filme = Filme.builder()
                .nome("Lucy")
                .genero("Ficção Científica")
                .ano(2014)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.adicionarFilme(filme);

        filmeService.removerFilme(1L);
        var filmes = filmeService.listarFilmes();
        Assertions.assertEquals(0, filmes.size());
    }

    @Test
    @DisplayName("Deve lançar exceção com id inválida")
    public void buscarPorIdTest(){
        var exception = Assertions.assertThrows(FilmeNotFoundException.class,
                () -> filmeService.buscarFilmePorId(7L));

        Assertions.assertEquals(exception.getReason(),"Filme não encontrado");
    }
}