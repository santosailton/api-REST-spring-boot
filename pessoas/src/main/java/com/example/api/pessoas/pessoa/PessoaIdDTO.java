package com.example.api.pessoas.pessoa;

import com.example.api.pessoas.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PessoaIdDTO(
    @NotNull
    Long id,
    String nome,
    String dataNascimento,
    @Valid
    EnderecoDTO endereco) {
    }
