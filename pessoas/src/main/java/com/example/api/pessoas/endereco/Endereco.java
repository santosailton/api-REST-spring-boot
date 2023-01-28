package com.example.api.pessoas.endereco;

import com.example.api.pessoas.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Enderecos")
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

    public Endereco(EnderecoDto dados) {
        this.logradouro = dados.getLogradouro();
        this.pessoa = dados.getPessoa();
        this.cep = dados.getCep();
        this.numero = dados.getNumero();
        this.cidade = dados.getCidade();
    }

    public static Endereco toEndereco(EnderecoDto endereco, Pessoa pessoa){
        endereco.setPessoa(pessoa);
        return new Endereco(endereco);
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
}
