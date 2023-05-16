package com.example.api.mappers;

import com.example.api.model.Endereco;
import com.example.api.dto.EnderecoDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EnderecoMapper {
    public static EnderecoDTO mapToDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.isPrincipal()
        );
    }

    public static Set<EnderecoDTO> mapListToDTO(Set<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoMapper::mapToDTO)
                .collect(Collectors.toSet());
    }

}

