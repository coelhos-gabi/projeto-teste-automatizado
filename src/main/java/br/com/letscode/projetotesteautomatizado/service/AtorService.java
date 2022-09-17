package br.com.letscode.projetotesteautomatizado.service;

import br.com.letscode.projetotesteautomatizado.exception.AtorNotFoundException;
import br.com.letscode.projetotesteautomatizado.model.Ator;
import br.com.letscode.projetotesteautomatizado.model.Filme;
import br.com.letscode.projetotesteautomatizado.repository.AtorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AtorService {
    private AtorRepository atorRepository;

    public Ator adicionarAtor(Ator ator){
        return atorRepository.save(ator);
    }

    public Ator buscarAtorPorId(Long id){
        var atorBuscado = atorRepository.findById(id);
        if(atorBuscado.isEmpty()){
            throw new AtorNotFoundException("Ator não encontrado");
        }

        return atorBuscado.get();
    }
    public List<Ator> listarAtores(){
        return atorRepository.findAll();
    }

    public Ator procurarAtorPorNome(String nome){
        Optional<Ator> atorBuscado = atorRepository.findAtorByNome(nome);

        if(atorBuscado.isPresent()){
            return atorBuscado.get();
        }
        throw new AtorNotFoundException("Ator não encontrado");
    }
    public Ator removerAtor(Long id){
        Ator ator = this.buscarAtorPorId(id);
        atorRepository.deleteById(id);
        return ator;
    }

    public Ator updateAtor(Ator novoAtor) {
        Ator ator = this.buscarAtorPorId(novoAtor.getId());
        ator.setNome(novoAtor.getNome());

        return this.adicionarAtor(ator);
    }
}
