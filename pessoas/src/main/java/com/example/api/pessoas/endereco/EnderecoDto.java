package com.example.api.pessoas.endereco;


import com.example.api.pessoas.pessoa.Pessoa;
import lombok.Data;

@Data
public class EnderecoDto {

    private String cep;
    private String cidade;
    private String logradouro;
    private String numero;
    private Pessoa pessoa;
    private String principal;

}
