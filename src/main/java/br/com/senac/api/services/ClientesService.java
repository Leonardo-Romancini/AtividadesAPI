package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.controllers.dtos.ClientesRequestDTO;
import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Clientes;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public void criar(ClientesRequestDTO cliente){
        Clientes clientePersist = this.clientesRequestDtoParaPessoa(cliente);

        clientesRepositorio.save(clientePersist);
    }

    public List<Clientes> listarTodos() {
        return clientesRepositorio.findAll();
    }

    public Clientes atualizar(Long id, ClientesRequestDTO cliente) throws Exception {
        if (clientesRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado.");
        }
        Clientes clientePersist = this.clientesRequestDtoParaPessoa(cliente);
        clientePersist.setId(id);//só botar o id
        return clientesRepositorio.save(clientePersist);
    }

    private Clientes clientesRequestDtoParaPessoa(ClientesRequestDTO in){
        Clientes out = new Clientes();
        out.setNome(in.getNome());
        out.setSobrenome(in.getSobrenome());
        out.setEmail(in.getEmail());
        out.setDocumento(in.getDocumento());

        return out;
    }

    public void deletar(Long id){
        if(!clientesRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }
        clientesRepositorio.deleteById(id);
    }
}
