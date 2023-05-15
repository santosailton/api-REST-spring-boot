package com.example.api.pessoas.pessoa;

import com.example.api.pessoas.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Pessoas")
@Entity(name = "Pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String dataNascimento;
    @OneToMany(mappedBy="pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();


    public Pessoa(String nome, String dataNascimento, List<Endereco> enderecos){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(Pessoa pessoa) {
        this(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getEnderecos());
    }

    public void setEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

}
