package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Tecnico;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {
    TecnicoRepository tecnicoRepository;

    @Autowired
    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico guardar(Tecnico t){
        return this.tecnicoRepository.save(t);
    }
    public Boolean eliminarTecnicoPorId(Integer id){
        Boolean existe = this.tecnicoRepository.existsById(id);
        this.tecnicoRepository.deleteById(id);
        return existe;
    }
    public List<Tecnico> obtenerTecnicos(){
        return this.tecnicoRepository.findAll();
    }
    public Tecnico obtenerTecnicoPorId(Integer id){
        return this.tecnicoRepository.findById(id).get();
    }
}
