package com.example.api.repositories;

import com.example.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value = "SELECT " +
            "CASE WHEN count(*) > 0 " +
            "THEN TRUE ELSE FALSE " +
            "END AS resultado " +
            "FROM pessoa e WHERE e.nome = :nome", nativeQuery=true)
    Integer findByNome(String nome);

}
