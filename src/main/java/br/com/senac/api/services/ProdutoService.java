package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.ClientesRequestDTO;
import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.controllers.dtos.ProdutoRequestDTO;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.modelos.Produto;
import br.com.senac.api.repositorios.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;


    public void criar (ProdutoRequestDTO produto) {
        Produto produtoPersist = this.produtoRequestDtoParaPessoa(produto);

        produtoRepositorio.save(produtoPersist);
    }

    public List<Produto> listarTodos() {
        return produtoRepositorio.findAll();
    }

    public Produto atualizar(Long id, ProdutoRequestDTO produto) throws Exception {
        if (produtoRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado.");
        }
        Produto produtoPersist = this.produtoRequestDtoParaPessoa(produto);
        produtoPersist.setId(id);//só botar o id
        return produtoRepositorio.save(produtoPersist);
    }

    private Produto produtoRequestDtoParaPessoa(ProdutoRequestDTO in){
        Produto out = new Produto();
        out.setNome(in.getNome());
        out.setDescricao(in.getDescricao());

        return out;
    }

    public void deletar(Long id){
        if(!produtoRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }
        produtoRepositorio.deleteById(id);
    }
}
