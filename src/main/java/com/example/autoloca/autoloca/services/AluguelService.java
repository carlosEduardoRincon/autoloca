package com.example.autoloca.autoloca.services;

import com.example.autoloca.autoloca.entities.Aluguel;
import com.example.autoloca.autoloca.entities.Pessoa;
import com.example.autoloca.autoloca.repositories.AluguelRepository;
import com.example.autoloca.autoloca.repositories.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return this.aluguelRepository.findById(id);
    }

    public void saveAluguel(Aluguel aluguel){
        var save = this.aluguelRepository.save(aluguel);
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguel.getPessoaId());
    }

    public void updateAluguel(Aluguel aluguel, Long id){
        var update = this.aluguelRepository.update(aluguel, id);
        if (update == 0) {
            throw new RuntimeException("Aluguel não encontrado");
        }
    }

    public void deleteAluguel(Long id){
        var delete = this.aluguelRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Aluguel não encontrado");
        }
    }
}
