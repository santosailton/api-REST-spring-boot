package com.example.api.repositories;

import com.example.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value = "SELECT * FROM pessoa e WHERE e.nome = ?1", nativeQuery=true)
    Optional findByNome(String nome);

}
