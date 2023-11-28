package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Incidente;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenteService {
    IncidenteRepository incidenteRepository;

    @Autowired
    public IncidenteService(IncidenteRepository incidenteRepository) {
        this.incidenteRepository = incidenteRepository;
    }

    public Incidente guardar(Incidente i){
        return this.incidenteRepository.save(i);
    }
    public Boolean eliminarIncidentePorId(Integer id){
        Boolean existe = this.incidenteRepository.existsById(id);
        this.incidenteRepository.deleteById(id);
        return existe;
    }
    public List<Incidente> obtenerIncidentes(){
        return this.incidenteRepository.findAll();
    }
    public Incidente obtenerIncidentePorId(Integer id){
        return this.incidenteRepository.findById(id).get();
    }
}
