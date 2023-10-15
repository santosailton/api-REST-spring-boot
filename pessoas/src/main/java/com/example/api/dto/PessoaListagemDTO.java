package com.example.api.dto;

import com.example.api.model.Endereco;
import com.example.api.model.Pessoa;

import java.util.HashSet;
import java.util.Set;

public record PessoaListagemDTO(String nome, String dataNascimento, Set<EnderecoDTO> endereco) {

    public PessoaListagemDTO(Pessoa pessoa) {
        this(pessoa.getNome(), pessoa.getDataNascimento(), convertToEnderecoDTOs(pessoa.getEnderecos()));
    }

    private static Set<EnderecoDTO> convertToEnderecoDTOs(Set<Endereco> enderecos) {
        Set<EnderecoDTO> enderecoDTOs = new HashSet<>();
        for (Endereco endereco : enderecos) {
            enderecoDTOs.add(new EnderecoDTO(endereco));
        }
        return enderecoDTOs;
    }

    public PessoaListagemDTO withEnderecos(Set<EnderecoDTO> enderecos) {
        return new PessoaListagemDTO(this.nome, this.dataNascimento, enderecos);
    }
}

