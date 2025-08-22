package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Clientes;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/criar")
    public ResponseEntity<Void> cadastrarPessoa (@RequestBody PessoaRequestDTO pessoa) {
        System.out.println(pessoa.toString());
        pessoaService.criar(pessoa);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listarTodos() {
        return ResponseEntity.ok(pessoaService.listarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public  ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody PessoaRequestDTO pessoa) {
        try {
            return  ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
