package com.example.api.model;

import com.example.api.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Endereco")
@Entity(name = "Endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PESSOA_ID")
    private Pessoa pessoa;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private String principal;


    public Endereco(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.pessoa = getPessoa();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.cidade = dados.cidade();
    }

    public Endereco(EnderecoDTO dados, Pessoa pessoa) {
        this.logradouro = dados.logradouro();
        this.pessoa = pessoa;
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.cidade = dados.cidade();
    }

    public static Endereco toEndereco(EnderecoDTO endereco) {
        EnderecoDTO enderecoAtualizado = new EnderecoDTO(
            endereco.cep(),
            endereco.cidade(),
            endereco.logradouro(),
            endereco.numero(),
            endereco.principal()
        );

    return new Endereco(enderecoAtualizado);
}

    public static Endereco secundario(Endereco endereco, String secundario){
        endereco.setPrincipal(secundario);
        return endereco;
    }
    public String toString(){
        return "logradouro="+this.logradouro+","+
                "cep="+this.cep+","+
                "numero="+this.numero+","+
                "cidade="+this.cidade;
    }

    public String isPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
