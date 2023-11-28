package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.TipoDeProblema;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.TipoDeProblemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDeProblemaService {
    TipoDeProblemaRepository tipoDeProblemaRepository;

    @Autowired
    public TipoDeProblemaService(TipoDeProblemaRepository tipoDeProblemaRepository) {
        this.tipoDeProblemaRepository = tipoDeProblemaRepository;
    }

    public TipoDeProblema guardar(TipoDeProblema p){
        return this.tipoDeProblemaRepository.save(p);
    }
    public Boolean eliminarTipoDeProblemaPorId(Integer id){
        Boolean existe =this.tipoDeProblemaRepository.existsById(id);
        this.tipoDeProblemaRepository.deleteById(id);
        return existe;
    }
    public List<TipoDeProblema> obtenerTipoDeProblemas(){
        return this.tipoDeProblemaRepository.findAll();
    }
    public TipoDeProblema obtenerTipoDeProblemaPorId(Integer id){
        return this.tipoDeProblemaRepository.findById(id).get();
    }
}
