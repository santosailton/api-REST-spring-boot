package com.example.api.pessoas.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
//    busca endereco por id pessoa
    @Query(value = "SELECT * FROM enderecos e WHERE e.pessoa_id = ?1", nativeQuery=true)
    List<Endereco> findAllByIdPessoa(Long idPessoa);
}
