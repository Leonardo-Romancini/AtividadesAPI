package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping("/criar")
    public ResponseEntity<Void> cadastrarCarro (@RequestBody CarroRequestDTO carro) {
        System.out.println(carro.toString());
        carroService.criar(carro);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listarTodos() {
        return ResponseEntity.ok(carroService.listarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public  ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody CarroRequestDTO carro) {
        try {
            return  ResponseEntity.ok(carroService.atualizar(id, carro));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            carroService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
