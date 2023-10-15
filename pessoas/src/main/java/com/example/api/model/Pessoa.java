package com.example.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "Pessoa")
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
    private Set<Endereco> enderecos = new HashSet<>();


    public Pessoa(String nome, String dataNascimento, Set<Endereco> enderecos){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    public Pessoa(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

}
