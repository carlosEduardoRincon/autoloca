package com.example.autoloca.autoloca.services;

import com.example.autoloca.autoloca.dtos.AluguelRequestDTO;
import com.example.autoloca.autoloca.entities.Aluguel;
import com.example.autoloca.autoloca.repositories.AluguelRepository;
import com.example.autoloca.autoloca.repositories.VeiculoRepository;
import com.example.autoloca.autoloca.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;

    public AluguelService(AluguelRepository aluguelRepository,
                          VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public List<Aluguel> findAllAlugueis(int page, int size) {
        int offset = (page - 1) * size;
        return this.aluguelRepository.findAll(size, offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return Optional.ofNullable(this.aluguelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado")));
    }

    public void saveAluguel(AluguelRequestDTO aluguelRequestDTO){
        var aluguelEntity = calculaAluguel(aluguelRequestDTO);
        var save = this.aluguelRepository.save(aluguelEntity);
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguelRequestDTO.pessoaId());
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

    private Aluguel calculaAluguel(AluguelRequestDTO aluguelRequestDTO){
        var veiculo = this.veiculoRepository.findById(aluguelRequestDTO.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        var quantidadeDeDias = BigDecimal.valueOf(aluguelRequestDTO.dataFim().getDayOfYear() - aluguelRequestDTO.dataInicio().getDayOfYear());
        var valor = veiculo.getValorDiaria().multiply(quantidadeDeDias);
        return new Aluguel(aluguelRequestDTO, valor);
    }
}
