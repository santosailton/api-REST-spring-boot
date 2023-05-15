package com.example.api.pessoas.endereco;

import lombok.Data;

//@Data
public record EditarEnderecoDTO(
        String cep,
        String cidade,
        String logradouro,
        String numero
) {
}
