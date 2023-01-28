package com.example.api.pessoas.pessoa;

import com.example.api.pessoas.endereco.EnderecoDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DTOPessoa{
        private String nome;
        private String dataNascimento;
        private List<EnderecoDto> endereco = new ArrayList<>();

}
