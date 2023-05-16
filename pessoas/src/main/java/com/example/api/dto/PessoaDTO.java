package com.example.api.dto;

import java.util.Set;

public record PessoaDTO(String nome, String dataNascimento, Set<EnderecoDTO> endereco) {

    public PessoaDTO withEnderecos(Set<EnderecoDTO> enderecos) {
        return new PessoaDTO(this.nome, this.dataNascimento, enderecos);
    }
}

