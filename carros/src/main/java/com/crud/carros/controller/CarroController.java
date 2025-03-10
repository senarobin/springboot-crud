package com.crud.carros.controller;

import com.crud.carros.entity.Carro;
import com.crud.carros.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> adicionarCarro(@RequestBody Carro carro) {
        Carro novoCarro = carroService.adicionarCarro(carro);
        return new ResponseEntity<>(novoCarro, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Carro>> listarTodosOsCarros() {
        List<Carro> carros = carroService.listarTodosOsCarros();
        return new ResponseEntity<>(carros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> listarCarroPorId(@PathVariable Long id) {
        return carroService.listarCarroPorId(id).map(carro -> new ResponseEntity<>(carro, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarInformacoes(@PathVariable Long id, @RequestBody Carro carro) {
        Carro carroAtualizado = carroService.atualizarInformacoes(id, carro);
        if (carroAtualizado != null) {
            return new ResponseEntity<>(carroAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        boolean deletado = carroService.deletarCarro(id);
        if (deletado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
