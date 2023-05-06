package com.example.api.pessoas.service;


import com.example.api.pessoas.endereco.EditarEnderecoDto;
import com.example.api.pessoas.endereco.EnderecoDto;
import com.example.api.pessoas.endereco.Endereco;
import com.example.api.pessoas.endereco.EnderecoRepository;
import com.example.api.pessoas.pessoa.EditarPessoaDto;
import com.example.api.pessoas.pessoa.DTOPessoa;
import com.example.api.pessoas.pessoa.Pessoa;
import com.example.api.pessoas.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Pessoa cadastrar(DTOPessoa dados) {

//        insere pessoa
        Pessoa pessoa1 = new Pessoa(dados.getNome(), dados.getDataNascimento());
        Pessoa pessoa = pessoaRepository.save(pessoa1);

//        insere e associa endereco
        if (!dados.getEndereco().isEmpty()) {
            List<Endereco> enderecos = new ArrayList<>();
            //outra maneira de iterar
//            enderecos = dados.getEndereco().stream().map(x -> Endereco.toEndereco(x, pessoa)).toList();
            
            //iterando com menos codigo
            dados.getEndereco().forEach(x -> enderecos.add(Endereco.toEndereco(x ,pessoa)));

            enderecoRepository.saveAll(enderecos);
        }

        return pessoa;
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Boolean editarPessoa(Long id, EditarPessoaDto dados) {

        var pessoaEncontrada = pessoaRepository.findById(id);
        if (pessoaEncontrada.isEmpty()) {
            return false;
        }

        var pessoaModel = pessoaEncontrada.get();
        if (dados.getNome() != null) {
            pessoaModel.setNome(dados.getNome());
        }
        if (dados.getDataNascimento() != null) {
            pessoaModel.setDataNascimento(dados.getDataNascimento());
        }
        pessoaRepository.save(pessoaModel);
        return true;
    }

    public ResponseEntity<?> editarEndereco(Long idPessoa, Long idEndereco, EditarEnderecoDto dados) {
        var pessoaEncontrada = pessoaRepository.findById(idPessoa);
        var enderecoEncontrado = enderecoRepository.findById(idEndereco);

        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        if (!enderecoEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        var enderecoModel = enderecoEncontrado.get();

        enderecoModel.setLogradouro(dados.getLogradouro());
        enderecoModel.setCep(dados.getCep());
        enderecoModel.setCidade(dados.getCidade());
        enderecoModel.setNumero(dados.getNumero());

        enderecoRepository.save(enderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModel);
    }

    public List<Endereco> listarEndereco(Long idPessoa) {
        return enderecoRepository.findAllByIdPessoa(idPessoa);
    }

    public ResponseEntity<?> adicionaEndereco(Long idPessoa, EnderecoDto dados) {
        var pessoaEncontrada = pessoaRepository.findById(idPessoa);
        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        var pessoa = pessoaEncontrada.get();
        var endereco = Endereco.toEndereco(dados, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(endereco));
    }

    public ResponseEntity<?> enderecoPrincipal(Long idPessoa, Long idEndereco) {
        var pessoaEncontrada = pessoaRepository.findById(idPessoa);
        var enderecoEncontrado = enderecoRepository.findById(idEndereco);

        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        if (enderecoEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        var enderecos = enderecoRepository.findAllByIdPessoa(idPessoa);

        if (enderecos.size() > 1){
            enderecos.stream().map(x -> Endereco.secundario(x, "S")).toList();
            enderecoRepository.saveAll(enderecos);
        }

        var enderecoModel = enderecoEncontrado.get();
        enderecoModel.setPrincipal("P");
        enderecoRepository.save(enderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModel);
    }


}
