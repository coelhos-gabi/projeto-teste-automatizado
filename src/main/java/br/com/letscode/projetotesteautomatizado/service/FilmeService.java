package br.com.letscode.projetotesteautomatizado.service;

import br.com.letscode.projetotesteautomatizado.exception.FilmeNotFoundException;
import br.com.letscode.projetotesteautomatizado.model.Filme;
import br.com.letscode.projetotesteautomatizado.repository.FilmeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilmeService {

    private FilmeRepository filmeRepository;

    public Filme adicionarFilme(Filme filme){
        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilmes(){
        return filmeRepository.findAll();
    }
    public Filme buscarFilmePorId(Long id){
        var filme = filmeRepository.findById(id);
        if(filme.isEmpty()){
            throw new FilmeNotFoundException("Filme não encontrado");
        }
        return filme.get();
    }
    public Filme procurarFilmePorNome(String nome){
        Optional<Filme> filmeBuscado = filmeRepository.findFilmeByNome(nome);

        if(filmeBuscado.isPresent()){
            return filmeBuscado.get();
        }
        throw new FilmeNotFoundException("Filme não encontrado");
    }

    public Filme removerFilme(Long id){
        Filme filme = this.buscarFilmePorId(id);
        filmeRepository.deleteById(id);
        return filme;
    }

    public Filme updateFilme(Filme novoFilme) {
        Filme filme = this.buscarFilmePorId(novoFilme.getId());
        filme.setNome(novoFilme.getNome());
        filme.setGenero(novoFilme.getGenero());
        filme.setAno(novoFilme.getAno());
        filme.setAtores(novoFilme.getAtores());

        return this.adicionarFilme(filme);
    }
}
