package com.example.api.dto;


import com.example.api.model.Endereco;

public record EnderecoDTO(
        String cep,
        String cidade,
        String logradouro,
        String numero,
        String principal) {

    public EnderecoDTO(Endereco endereco) {
        this(endereco.getCep(), endereco.getCidade(), endereco.getLogradouro(), endereco.getNumero(), endereco.isPrincipal());
    }
}
