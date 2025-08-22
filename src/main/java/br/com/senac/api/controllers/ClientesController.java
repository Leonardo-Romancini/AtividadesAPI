package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.controllers.dtos.ClientesRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Clientes;
import br.com.senac.api.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping("/criar")
    public ResponseEntity<Void> cadastrarCliente(@RequestBody ClientesRequestDTO cliente) {
        System.out.println(cliente.toString());
        clientesService.criar(cliente);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listarTodos() {
        return ResponseEntity.ok(clientesService.listarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public  ResponseEntity<Clientes> atualizar(@PathVariable Long id, @RequestBody ClientesRequestDTO cliente) {
        try {
            return  ResponseEntity.ok(clientesService.atualizar(id, cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
