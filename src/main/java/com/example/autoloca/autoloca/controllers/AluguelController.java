package com.example.autoloca.autoloca.controllers;

import com.example.autoloca.autoloca.dtos.AluguelRequestDTO;
import com.example.autoloca.autoloca.entities.Aluguel;
import com.example.autoloca.autoloca.services.AluguelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    private static final Logger log = LoggerFactory.getLogger(AluguelController.class);

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAluguels(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("[GET] Iniciando processo de listagem de alugueis");
        var aluguels = this.aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(aluguels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguelById(
            @PathVariable("id") Long id)
    {
        log.info("[GET] Iniciando processo de listagem de aluguel com o id: " + id);
        var aluguel = this.aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);
    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(
            @Valid @RequestBody AluguelRequestDTO aluguelRequestDTO
    ) {
        log.info("[POST] Iniciando persistência do aluguel");
        this.aluguelService.saveAluguel(aluguelRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @RequestBody Aluguel aluguel,
            @PathVariable Long id
    ) {
        log.info("[PUT] Iniciando atualização do aluguel");
        this.aluguelService.updateAluguel(aluguel, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(
            @PathVariable Long id
    ) {
        log.info("[DELETE] Iniciando remoção do aluguel");
        this.aluguelService.deleteAluguel(id);
        return ResponseEntity.ok().build();
    }
}
