package com.example.api.pessoas.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT * FROM ENDERECOS e WHERE e.PESSOA_ID = ?1", nativeQuery=true)
    List<Endereco> findAllByIdPessoa(Long idPessoa);
}
