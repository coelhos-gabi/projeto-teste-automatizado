package br.com.letscode.projetotesteautomatizado.controller;

import br.com.letscode.projetotesteautomatizado.model.Filme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmeControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Deve retornar status 201-Created")
    public void cadastrarFilmeStatusTest() {

        Filme filme = Filme.builder().nome("Orfeu").genero("Drama").ano(1999).build();

        ResponseEntity<Filme> filmeResposta = this.testRestTemplate
                .postForEntity("http://localhost:" + port + "/filmes",
                        filme, Filme.class);

        Assertions.assertEquals(CREATED, filmeResposta.getStatusCode());
        Assertions.assertEquals("Orfeu", filmeResposta.getBody().getNome());
        Assertions.assertEquals(1999, filmeResposta.getBody().getAno());
    }

    @Test
    @DisplayName("Deve retornar o filme")
    void cadastrarFilmeRetornoTest() {

        Filme filme = Filme.builder().nome("Orfeu").genero("Drama").ano(1999).build();

        ResponseEntity<Filme> filmeResposta = this.testRestTemplate
                .postForEntity("http://localhost:" + port + "/filmes",
                        filme, Filme.class);

        Assertions.assertEquals(filme.getNome(), filmeResposta.getBody().getNome());

    }

    @Test
    @DisplayName("Deve retornar 200-OK ao acessar os filmes do repositorio")
    void acessarFilmesStatusTest() {
        Filme filme = Filme.builder().nome("Talvez uma História de Amor").genero("Romance")
                .ano(2018).build();
        this.testRestTemplate.postForEntity("http://localhost:" + port + "/filmes",
                        filme, Filme.class);

        ResponseEntity<Filme[]> listaResposta = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/filmes",Filme[].class);

        Assertions.assertEquals(OK, listaResposta.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar os filmes do repositorio")
    void acessarFilmesRetornoTest() {
        Filme filme = Filme.builder().nome("Talvez uma História de Amor").genero("Romance")
                .ano(2018).build();
        this.testRestTemplate.postForEntity("http://localhost:" + port + "/filmes",
                        filme, Filme.class);

        ResponseEntity<Filme[]> listaResposta = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/filmes",Filme[].class);
        var listaFilmes = listaResposta.getBody();

        Assertions.assertEquals(filme.getNome(), listaFilmes[0].getNome());
    }

    @Test
    @DisplayName("Deve retornar status 200-OK ao buscar id válida")
    void procurarFilmePorIdStatusTest() {
        long id = 1L;
        Filme filme = Filme.builder().nome("Carandiru").genero("Drama")
                .ano(2003).build();

        this.testRestTemplate.postForEntity("http://localhost:" + port + "/filmes",
                filme, Filme.class);

        var filmeResposta = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/filmes/"+id,
                Filme.class);
        Assertions.assertEquals(OK, filmeResposta.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar o filme ao buscar id válida")
    void procurarFilmePorIdRetornoTest() {
        Long id = 1L;
        Filme filme = Filme.builder().nome("Carandiru").genero("Drama")
                .ano(2003).build();

        this.testRestTemplate.postForEntity("http://localhost:" + port + "/filmes",
                filme, Filme.class);

        var filmeResposta = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/filmes/"+id,
                Filme.class);
        Assertions.assertEquals(filme.getNome(), filmeResposta.getBody().getNome());
    }

    @Test
    @DisplayName("Deve retornar status 200-OK ao atualizar")
    void atualizarFilmeStatusTest() {
        Filme filme = Filme.builder().nome("Tropa de Elite").genero("Ação")
                .ano(2007).build();

        this.testRestTemplate.postForEntity("http://localhost:" + port + "/filmes",
                filme, Filme.class);
    }

    @Test
    @DisplayName("Deve retornar status 200-OK ao deletar")
    void removerFilmeStatusTest() {
        long id = 1L;

        Filme filme = Filme.builder().nome("Cidade de Deus").genero("Drama")
                .ano(2002).build();
        this.testRestTemplate.postForEntity("http://localhost:" + port + "/filmes",
                filme, Filme.class);

        this.testRestTemplate.delete("http://localhost:" + port + "/filmes/" +
                id, filme, Filme.class);

        var listaFilmes = this.testRestTemplate.getForEntity("http://localhost:" +
                port + "/filmes", Filme[].class);

        Assertions.assertEquals(OK, listaFilmes.getStatusCode());
    }
}