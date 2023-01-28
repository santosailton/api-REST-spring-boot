package com.example.api.pessoas.pessoa;

import com.example.api.pessoas.endereco.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DTOPessoaId(
    @NotNull
    Long id,
    String nome,
    String dataNascimento,
    @Valid
    EnderecoDto endereco) {
    }
