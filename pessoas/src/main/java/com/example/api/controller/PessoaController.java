package com.example.api.controller;

import com.example.api.dto.EditarEnderecoDTO;
import com.example.api.dto.EditarPessoaDTO;
import com.example.api.dto.EnderecoDTO;
import com.example.api.dto.PessoaDTO;
import com.example.api.repositories.PessoaRepository;
import com.example.api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<PessoaDTO> cadastrar(@RequestBody @Valid PessoaDTO dados) {
        var pessoaDTO = pessoaService.cadastrar(dados);

//        return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.CREATED).body(dados);

    }

    @GetMapping
    public ResponseEntity<?> lista() {
        var pessoas = pessoaService.listar();

        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<?> busca(@PathVariable Long idPessoa) {
        return pessoaService.buscarPessoaId(idPessoa);
    }

    @PostMapping("/{idPessoa}/endereco")
    @Transactional
    public ResponseEntity<?> adicionaEndereco(@PathVariable Long idPessoa, @RequestBody EnderecoDTO dados) {
        return pessoaService.adicionaEndereco(idPessoa, dados);
    }

    @GetMapping("/{idPessoa}/enderecos")
    public ResponseEntity<?> listarEndereco(@PathVariable Long idPessoa) {
        var enderecos = pessoaService.listarEndereco(idPessoa);

        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
    }

    @PatchMapping("/{idPessoa}")
    @Transactional
    public ResponseEntity<?> atualiza(@PathVariable Long idPessoa, @RequestBody EditarPessoaDTO dados) {
        var atualizou = pessoaService.editarPessoa(idPessoa, dados);

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

    @PatchMapping("/{idPessoa}/{idEndereco}")
    @Transactional
    public ResponseEntity<?> enderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        return pessoaService.enderecoPrincipal(idPessoa, idEndereco);
    }
}