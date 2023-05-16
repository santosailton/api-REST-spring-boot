package com.example.api.dto;


public record EnderecoDTO(
        String cep,
        String cidade,
        String logradouro,
        String numero,
        String principal) {
}
