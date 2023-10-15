package com.example.api.service;


import com.example.api.dto.*;
import com.example.api.mappers.EnderecoMapper;
import com.example.api.model.Endereco;
import com.example.api.repositories.EnderecoRepository;
import com.example.api.mappers.PessoaMapper;
import com.example.api.model.Pessoa;
import com.example.api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Service
public class PessoaService {
    public static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada";

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;


    public boolean cadastrar(PessoaDTO dados){
        // Verificar se a pessoa já existe no banco de dados
        Integer pessoaExistente = 0;
        pessoaExistente = pessoaRepository.findByNome(dados.nome());

        if (pessoaExistente.equals(1)) {
            return false; //ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF já cadastrado");
        }
        else {
            // Criar a pessoa
            var pessoa = new Pessoa(dados.nome(), dados.dataNascimento());

            // Criar um conjunto para armazenar endereços únicos
            Set<Endereco> enderecosUnicos = new HashSet<>();

            // Iterar sobre a lista de endereços fornecida
            for (EnderecoDTO enderecoDTO : dados.endereco()) {
                // Verificar se o endereço já existe no conjunto de endereços únicos
                boolean enderecoJaExistente = enderecosUnicos.stream()
                        .anyMatch(e -> e.getCep().equals(enderecoDTO.cep())
                                && e.getCidade().equals(enderecoDTO.cidade())
                                && e.getLogradouro().equals(enderecoDTO.logradouro())
                                && e.getNumero().equals(enderecoDTO.numero()));

                if (!enderecoJaExistente) {
                    // Se o endereço não existe, crie um novo endereço e adicione-o ao conjunto de endereços únicos
                    var endereco = new Endereco(enderecoDTO, pessoa);
                    enderecosUnicos.add(endereco);
                }

            }

            // Associar os endereços únicos à pessoa

            pessoa.setEnderecos(enderecosUnicos);
            var pessoaSalva = pessoaRepository.save(pessoa);

            // Retorna a PessoaDTO correspondente à pessoa salva
            //return PessoaMapper.mapPessoaToPessoaDTO(pessoaSalva);
            return  true;
        }
    }

    @Transactional(readOnly = true)
    public Page<PessoaListagemDTO> listar(Pageable paginacao) {
        // Recupera todas as pessoas do banco de dados
        return pessoaRepository.findAll(paginacao).map(PessoaListagemDTO::new);

    }


    public Boolean editarPessoa(Long id, EditarPessoaDTO dados) {

        var pessoaEncontrada = pessoaRepository.findById(id);
        if (pessoaEncontrada.isEmpty()) {
            return false;
        }

        var pessoaModel = pessoaEncontrada.get();
        if (dados.nome() != null) {
            pessoaModel.setNome(dados.nome());
        }
        if (dados.dataNascimento() != null) {
            pessoaModel.setDataNascimento(dados.dataNascimento());
        }
        pessoaRepository.save(pessoaModel);
        return true;
    }

    public ResponseEntity<Object> editarEndereco(Long idPessoa, Long idEndereco, EditarEnderecoDTO dados) {
        var pessoaEncontrada = pessoaRepository.findById(idPessoa);
        var enderecoEncontrado = enderecoRepository.findById(idEndereco);

        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PESSOA_NAO_ENCONTRADA);

        }
        if (enderecoEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        var enderecoModel = enderecoEncontrado.get();

        enderecoModel.setLogradouro(dados.logradouro());
        enderecoModel.setCep(dados.cep());
        enderecoModel.setCidade(dados.cidade());
        enderecoModel.setNumero(dados.numero());

        enderecoRepository.save(enderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModel);
    }

    @Transactional(readOnly = true)
    public Set<EnderecoDTO> listarEndereco(Long idPessoa) {
        Set<Endereco> enderecos = enderecoRepository.findAllByIdPessoa(idPessoa);

        return EnderecoMapper.mapListToDTO(enderecos);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> buscarPessoaId(Long idPessoa) {
        var pessoa = pessoaRepository.findById(idPessoa);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PESSOA_NAO_ENCONTRADA);
        }

        PessoaDTO pessoaDTO = PessoaMapper.mapPessoaToPessoaDTO(pessoa.get());
        return ResponseEntity.status( HttpStatus.OK).body(pessoaDTO);
    }

    public ResponseEntity<Object> adicionaEndereco(Long idPessoa, EnderecoDTO dados) {
        var pessoaEncontrada = pessoaRepository.findById(idPessoa);

        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PESSOA_NAO_ENCONTRADA);
        }

        var pessoa = pessoaEncontrada.get();
        var endereco = Endereco.toEndereco(dados);
        endereco.setPessoa(pessoa);

        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(endereco));
    }

    public ResponseEntity<Object> enderecoPrincipal(Long idPessoa, Long idEndereco) {
        var pessoaEncontrada = pessoaRepository.findById(idPessoa);
        var enderecoEncontrado = enderecoRepository.findById(idEndereco);

        if (pessoaEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PESSOA_NAO_ENCONTRADA);
        }
        if (enderecoEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        }

        var enderecos = enderecoRepository.findAllByIdPessoa(idPessoa);

        if (enderecos.size() > 1){

            enderecoRepository.saveAll(enderecos.stream().map(x -> Endereco.secundario(x, "S")).toList());
        }

        var enderecoModel = enderecoEncontrado.get();
        enderecoModel.setPrincipal("P");
        enderecoRepository.save(enderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModel);
    }

}
