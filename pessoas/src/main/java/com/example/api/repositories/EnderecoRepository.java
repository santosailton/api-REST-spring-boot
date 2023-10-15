package com.example.api.repositories;

import com.example.api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
//    busca endereco por id pessoa
    @Query(value = "SELECT * FROM endereco e WHERE e.pessoa_id = :idPessoa", nativeQuery=true)
    Set<Endereco> findAllByIdPessoa(Long idPessoa);

}
