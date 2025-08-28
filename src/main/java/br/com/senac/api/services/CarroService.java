package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.controllers.dtos.PessoaRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.modelos.Pessoa;
import br.com.senac.api.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio carroRepositorio;


    public void criar (CarroRequestDTO carro) {
        Carro carroPersist = this.carroRequestDtoParaPessoa(carro);

        carroRepositorio.save(carroPersist);
    }

    public List<Carro> listarTodos() {
        return carroRepositorio.findAll();
    }

    public Carro atualizar(Long id, CarroRequestDTO carro) throws Exception {
        if (carroRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado.");
        }
        Carro carroPersist = this.carroRequestDtoParaPessoa(carro);
        carroPersist.setId(id);//só botar o id
        return carroRepositorio.save(carroPersist);
    }

    private Carro carroRequestDtoParaPessoa(CarroRequestDTO in){
        Carro out = new Carro();
        out.setModelo(in.getModelo());
        out.setMarca(in.getMarca());

        return out;
    }

    public void deletar(Long id){
        if(!carroRepositorio.existsById(id)) {
            throw new RuntimeException("Registro não encontrado");
        }
        carroRepositorio.deleteById(id);
    }
}
