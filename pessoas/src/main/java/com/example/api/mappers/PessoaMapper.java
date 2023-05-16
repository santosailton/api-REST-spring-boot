package com.example.api.mappers;

import com.example.api.model.Endereco;
import com.example.api.dto.EnderecoDTO;
import com.example.api.model.Pessoa;
import com.example.api.dto.PessoaDTO;

import java.util.Set;

public class PessoaMapper {

    public static PessoaDTO mapPessoaToPessoaDTO(Pessoa pessoa) {
        Set<EnderecoDTO> enderecosDTO = EnderecoMapper.mapListToDTO(pessoa.getEnderecos());
        return new PessoaDTO(pessoa.getNome(), pessoa.getDataNascimento(), enderecosDTO);
    }

}

