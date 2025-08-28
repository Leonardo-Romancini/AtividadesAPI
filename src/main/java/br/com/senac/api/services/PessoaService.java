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
        Pessoa pessoaPersist = this.pessoaRequestDtoParaPessoa(pessoa);

        pessoaRepositorio.save(pessoaPersist);
    }

    public List<Pessoa> listarTodos() {
        return pessoaRepositorio.findAll();
    }

    public Pessoa atualizar(Long id, PessoaRequestDTO pessoa) throws Exception {
        if (pessoaRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado.");
        }
        Pessoa pessoaPersist = this.pessoaRequestDtoParaPessoa(pessoa);
        pessoaPersist.setId(id);//só botar o id para fazer ser update
        return pessoaRepositorio.save(pessoaPersist);
    }

    private Pessoa pessoaRequestDtoParaPessoa(PessoaRequestDTO in){
        Pessoa out = new Pessoa();
        out.setNome(in.getNome());
        out.setSobrenome(in.getSobrenome());

        return out;
    }
}
