package br.com.senac.api.services;

import br.com.senac.api.controllers.dtos.CarroRequestDTO;
import br.com.senac.api.modelos.Carro;
import br.com.senac.api.repositorios.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio carroRepositorio;


    public void criar (CarroRequestDTO carro) {
        Carro carroPersist = new Carro();
        carroPersist.setMarca(carro.getMarca());
        carroPersist.setModelo(carro.getModelo());

        carroRepositorio.save(carroPersist);
    }

    public List<Carro> listarTodos() {
        return carroRepositorio.findAll();
    }

    public Carro atualizar(Long id, CarroRequestDTO carro) throws Exception {
        if (carroRepositorio.existsById(id) == false) {
            throw new Exception("Registro não encontrado.");
        }
        Carro carroPersist = new Carro();
        carroPersist.setModelo(carro.getModelo());
        carroPersist.setMarca(carro.getMarca());
        carroPersist.setId(id);//só botar o id
        return carroRepositorio.save(carroPersist);
    }
}
