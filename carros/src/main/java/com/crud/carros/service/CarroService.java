package com.crud.carros.service;

import com.crud.carros.entity.Carro;
import com.crud.carros.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public Carro adicionarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> listarTodosOsCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> listarCarroPorId(Long id) {
        return carroRepository.findById(id);
    }

    public Carro atualizarInformacoes(Long id, Carro carroAtualizado) {
        if (carroRepository.existsById(id)) {
            carroAtualizado.setId(id);
            return carroRepository.save(carroAtualizado);
        }
        return null;
    }

    public boolean deletarCarro(Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
