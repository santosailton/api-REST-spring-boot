package com.example.api.pessoas.endereco;

import lombok.Data;

@Data
public class EditarEnderecoDto {
    private String cep;
    private String cidade;
    private String logradouro;
    private String numero;
}
