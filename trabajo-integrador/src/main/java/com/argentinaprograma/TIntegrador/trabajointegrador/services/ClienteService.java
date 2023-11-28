package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Cliente;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente guardar(Cliente c){
        return this.clienteRepository.save(c);
    }
    public Boolean eliminarClientePorId(Integer id){
        Boolean existe = this.clienteRepository.existsById(id);
        this.clienteRepository.deleteById(id);
        return existe;
    }
    public List<Cliente> obtenerClientes(){
        return this.clienteRepository.findAll();
    }
    public Cliente obtenerClientePorId(Integer id){
        return this.clienteRepository.findById(id).get();
    }

}
