package com.example.api.pessoas.pessoa;

import com.example.api.pessoas.endereco.EnderecoDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@Data
//public class PessoaDTO {
//        private String nome;
//        private String dataNascimento;
//        private List<EnderecoDTO> endereco = new ArrayList<>();
//
//}
public record PessoaDTO(String nome,
        String dataNascimento,
        List<EnderecoDTO> endereco) {

        public PessoaDTO(String nome, String dataNascimento, List<EnderecoDTO> endereco) {
                this.endereco = (endereco != null) ? endereco : List.of();
                this.nome = nome;
                this.dataNascimento = dataNascimento;
        }
}
