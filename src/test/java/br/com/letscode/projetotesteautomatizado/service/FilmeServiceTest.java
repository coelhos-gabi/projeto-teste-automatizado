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
        Ator ator1 = atorRepository.save(Ator.builder().nome("Matheus Nachtergalle").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Selton Mello").build());

        Filme filme = Filme.builder()
                .nome("O Auto da Compadecida")
                .genero("Comedia")
                .ano(2000)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.adicionarFilme(filme);

        List<Filme> filmes = filmeService.listarFilmes();

        Assertions.assertEquals(1, filmes.size());
        Assertions.assertEquals("O Auto da Compadecida", filmes.get(0).getNome());
    }

    @Test
    @DisplayName("Deve retornar um filme a partir do nome")
    public void procurarFilmePorNomeTest() {
        Ator ator1 = atorRepository.save(Ator.builder().nome("Silvero Pereira").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Bárbara Colen").build());
        Ator ator3 = atorRepository.save(Ator.builder().nome("KArine Teles").build());
        Ator ator4 = atorRepository.save(Ator.builder().nome("Sonia Braga").build());

        Filme filme = Filme.builder()
                .nome("Bacurau")
                .genero("Thriller/Drama")
                .ano(2019)
                .atores(List.of(ator1, ator2, ator3, ator4))
                .build();

        filmeService.adicionarFilme(filme);

        var filmeBuscado = filmeService.procurarFilmePorNome("Lucy");

        Assertions.assertEquals("Lucy",filmeBuscado.getNome());
    }

    @Test
    @DisplayName("Deve remover um filme do repositório")
    public void removerFilmeTest(){
        Ator ator1 = atorRepository.save(Ator.builder().nome("Fernanda Montenegro").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Vinicius de Oliveira").build());
        Ator ator3 = atorRepository.save(Ator.builder().nome("Marilia Pera").build());

        Filme filme = Filme.builder()
                .nome("Central do Brasil")
                .genero("Drama")
                .ano(1998)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.adicionarFilme(filme);

        filmeService.removerFilme(1L);
        var filmes = filmeService.listarFilmes();
        Assertions.assertEquals(0, filmes.size());
    }

    @Test
    @DisplayName("Deve lançar exceção com id inválida")
    public void buscarFilmePorIdTest(){
        var exception = Assertions.assertThrows(FilmeNotFoundException.class,
                () -> filmeService.buscarFilmePorId(7L));

        Assertions.assertEquals(exception.getReason(),"Filme não encontrado");
    }

    @Test
    @DisplayName("Deve atualizar o cadastro de um filme")
    void updateFilme() {
        Ator ator1 = atorRepository.save(Ator.builder().nome("Virginia Cavendish").build());
        Ator ator2 = atorRepository.save(Ator.builder().nome("Debora Falabella").build());

        Filme filme = Filme.builder()
                .id(1L)
                .nome("Lisbela e o Prisioneiro")
                .genero("Romance/Comedia")
                .ano(200)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.adicionarFilme(filme);

        Filme novoFilme = Filme.builder()
                .id(1L)
                .nome("Lisbela e o Prisioneiro")
                .genero("Romance/Comedia")
                .ano(2003)
                .atores(List.of(ator1, ator2))
                .build();

        filmeService.updateFilme(novoFilme);
        var filmeAtualizado = filmeService.buscarFilmePorId(1L);
        Assertions.assertEquals(novoFilme.getAno(),filmeAtualizado.getAno());
    }
}