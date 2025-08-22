package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.ClientesRequestDTO;
import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.modelos.Clientes;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.repositorios.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepositorio pessoaRepositorio;


    public void criar (PessoaRequestDTO pessoa) {
        Pessoa pessoaPersist = new Pessoa();
        pessoaPersist.setNome(pessoa.getNome());
        pessoaPersist.setSobrenome(pessoa.getSobrenome());

        pessoaRepositorio.save(pessoaPersist);
    }

    public List<Pessoa> listarTodos() {
        return pessoaRepositorio.findAll();
    }

    public Pessoa atualizar(Long id, PessoaRequestDTO pessoa) throws Exception {
        if (pessoaRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado.");
        }
        Pessoa pessoaPersist = new Pessoa();
        pessoaPersist.setNome(pessoa.getNome());
        pessoaPersist.setSobrenome(pessoa.getSobrenome());
        pessoaPersist.setId(id);//só botar o id
        return pessoaRepositorio.save(pessoaPersist);
    }
}
