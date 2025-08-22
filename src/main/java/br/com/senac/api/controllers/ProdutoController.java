package br.com.senac.api.controllers;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.controllers.dtos.ProdutoRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Produto;
import br.com.senac.api.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar")
    public ResponseEntity<Void> cadastrarProduto (@RequestBody ProdutoRequestDTO produto) {
        System.out.println(produto.toString());
        produtoService.criar(produto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public  ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produto) {
        try {
            return  ResponseEntity.ok(produtoService.atualizar(id, produto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
