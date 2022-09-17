package br.com.letscode.projetotesteautomatizado.repository;

import br.com.letscode.projetotesteautomatizado.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    Optional<Filme> findFilmeByNome(@NotBlank String nome);
}
