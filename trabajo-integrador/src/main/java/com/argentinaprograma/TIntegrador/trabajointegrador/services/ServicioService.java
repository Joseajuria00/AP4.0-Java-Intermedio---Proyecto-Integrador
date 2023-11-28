package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Servicio;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {
    ServicioRepository servicioRepository;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public Servicio guardar(Servicio s){
        return this.servicioRepository.save(s);
    }
    public Boolean eliminarServicioPorId(Integer id){
        Boolean existe = this.servicioRepository.existsById(id);
        this.servicioRepository.deleteById(id);
        return existe;
    }
    public List<Servicio> obtenerServicios(){
        return this.servicioRepository.findAll();
    }
    public Servicio obtenerServicioPorId(Integer id){
        return this.servicioRepository.findById(id).get();
    }
}
