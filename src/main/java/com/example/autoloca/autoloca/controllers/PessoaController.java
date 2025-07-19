package com.example.autoloca.autoloca.controllers;

import com.example.autoloca.autoloca.entities.Pessoa;
import com.example.autoloca.autoloca.services.PessoaService;
import com.example.autoloca.autoloca.services.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private static final Logger log = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("[GET] Iniciando processo de listagem de pessoas");
        var pessoas = this.pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoaById(
            @PathVariable("id") Long id)
    {
        log.info("[GET] Iniciando processo de listagem de pessoa com o id: " + id);
        var pessoa = this.pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(
            @RequestBody Pessoa pessoa
    ) {
        log.info("[POST] Iniciando persistência da pessoa");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
            @RequestBody Pessoa pessoa,
            @PathVariable Long id
    ) {
        log.info("[PUT] Iniciando atualização da pessoa");
        this.pessoaService.updatePessoa(pessoa, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(
            @PathVariable Long id
    ) {
        log.info("[DELETE] Iniciando remoção da pessoa");
        this.pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }
}

