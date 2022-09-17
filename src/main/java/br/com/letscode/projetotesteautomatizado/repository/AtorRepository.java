package br.com.letscode.projetotesteautomatizado.repository;

import br.com.letscode.projetotesteautomatizado.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
    Optional<Ator> findAtorByNome(String nome);
}
