package com.example.api.pessoas.controller;

import com.example.api.pessoas.endereco.EditarEnderecoDTO;
import com.example.api.pessoas.endereco.EnderecoDTO;
import com.example.api.pessoas.pessoa.*;
import com.example.api.pessoas.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @Transactional
    public ResponseEntity<Pessoa> cadastrar(@RequestBody @Valid PessoaDTO dados) {
        Pessoa pessoa = pessoaService.cadastrar(dados);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> lista() {
        List<Pessoa> pessoas = pessoaService.listar();

//        System.out.println("retorno: "+pessoas);
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> busca(@PathVariable Long id) {
        var pessoa = repository.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        return ResponseEntity.status( HttpStatus.OK).body(pessoa.get());
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long id, @RequestBody EditarPessoaDTO dados) {
        var atualizou = pessoaService.editarPessoa(id, dados);

        if (!atualizou) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dados);
    }

    @PutMapping("/{idPessoa}/{idEndereco}")
    @Transactional
    public ResponseEntity<?> atualizaEndereco(@PathVariable Long idPessoa, @PathVariable Long idEndereco,
                                              @RequestBody EditarEnderecoDTO dados) {
        return pessoaService.editarEndereco(idPessoa, idEndereco, dados);
    }

    @GetMapping("/{idPessoa}/enderecos")
    public ResponseEntity<?> listarEndereco(@PathVariable Long idPessoa) {
        var enderecos = pessoaService.listarEndereco(idPessoa);

        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
    }

    @PostMapping("/{idPessoa}/endereco")
    @Transactional
    public ResponseEntity<?> adicionaEndereco(@PathVariable Long idPessoa, @RequestBody EnderecoDTO dados) {
        return pessoaService.adicionaEndereco(idPessoa, dados);
    }

    @PatchMapping("/{idPessoa}/{idEndereco}")
    @Transactional
    public ResponseEntity<?> enderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        return pessoaService.enderecoPrincipal(idPessoa, idEndereco);
    }
}