package com.example.api.controller;

import com.example.api.dto.*;
import com.example.api.repositories.PessoaRepository;
import com.example.api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;
    @Autowired
    private PessoaService pessoaService;


    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid PessoaDTO dados) {
        if (pessoaService.cadastrar(dados)){
            return ResponseEntity.status(HttpStatus.CREATED).body(dados);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já cadastrado");

        }

    }

    @GetMapping
    public Page<PessoaListagemDTO> lista(Pageable paginacao) {
        return pessoaService.listar(paginacao);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<Object> busca(@PathVariable Long idPessoa) {
        return pessoaService.buscarPessoaId(idPessoa);
    }

    @PostMapping("/{idPessoa}/endereco")
    @Transactional
    public ResponseEntity<Object> adicionaEndereco(@PathVariable Long idPessoa, @RequestBody EnderecoDTO dados) {
        return pessoaService.adicionaEndereco(idPessoa, dados);
    }

    @GetMapping("/{idPessoa}/enderecos")
    public ResponseEntity<Object> listarEndereco(@PathVariable Long idPessoa) {
        var enderecos = pessoaService.listarEndereco(idPessoa);

        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
    }

    @PatchMapping("/{idPessoa}")
    @Transactional
    public ResponseEntity<Object> atualiza(@PathVariable Long idPessoa, @RequestBody EditarPessoaDTO dados) {
        boolean atualizou = pessoaService.editarPessoa(idPessoa, dados);

        if (!atualizou) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(dados);
    }

    @PutMapping("/{idPessoa}/{idEndereco}")
    @Transactional
    public ResponseEntity<Object> atualizaEndereco(@PathVariable Long idPessoa, @PathVariable Long idEndereco,
                                              @RequestBody EditarEnderecoDTO dados) {
        return pessoaService.editarEndereco(idPessoa, idEndereco, dados);
    }

    @PatchMapping("/{idPessoa}/{idEndereco}")
    @Transactional
    public ResponseEntity<Object> enderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        return pessoaService.enderecoPrincipal(idPessoa, idEndereco);
    }
}