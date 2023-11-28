package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Especialidad;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {
    EspecialidadRepository especialidadRepository;

    @Autowired
    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    public Especialidad guardar(Especialidad e){
        return this.especialidadRepository.save(e);
    }
    public Boolean eliminarEspecialidadPorId(Integer id){
        Boolean existe = this.especialidadRepository.existsById(id);
        this.especialidadRepository.deleteById(id);
        return existe;
    }
    public List<Especialidad> obtenerEspecialidades(){
        return this.especialidadRepository.findAll();
    }
    public Especialidad obtenerEspecialidadPorId(Integer id){
        return this.especialidadRepository.findById(id).get();
    }
}
