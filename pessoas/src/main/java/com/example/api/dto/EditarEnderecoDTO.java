package com.example.api.dto;

public record EditarEnderecoDTO(
        String cep,
        String cidade,
        String logradouro,
        String numero) {
}
