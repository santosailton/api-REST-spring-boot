package com.example.api.pessoas.endereco;


import com.example.api.pessoas.pessoa.Pessoa;
import lombok.Data;

//@Data
public record EnderecoDTO(
        String cep,
        String cidade,
        String logradouro,
        String numero,
        Pessoa pessoa,
        String principal) {

}
