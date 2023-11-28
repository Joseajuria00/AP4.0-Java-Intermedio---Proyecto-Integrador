package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Especialidad;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.IncidenteDetalle;
import com.argentinaprograma.TIntegrador.trabajointegrador.repositories.IncidenteDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class IncidenteDetalleService {
    IncidenteDetalleRepository incidenteDetalleRepository;

    @Autowired
    public IncidenteDetalleService(IncidenteDetalleRepository incidenteDetalleRepository) {
        this.incidenteDetalleRepository = incidenteDetalleRepository;
    }

    public IncidenteDetalle guardar(IncidenteDetalle iDetalle){
        return this.incidenteDetalleRepository.save(iDetalle);
    }
    public Boolean eliminarIncidenteDetallePorId(Integer id){
        Boolean existe = this.incidenteDetalleRepository.existsById(id);
        this.incidenteDetalleRepository.deleteById(id);
        return existe;
    }
    public List<IncidenteDetalle> obtenerIncidentesDetalles(){
        return this.incidenteDetalleRepository.findAll();
    }
    public IncidenteDetalle obtenerIncidenteDetallePorId(Integer id){
        return this.incidenteDetalleRepository.findById(id).get();
    }
    public Boolean tecnicoHabilitado(IncidenteDetalle incidenteDetalle){
        List<Especialidad> especialidadesTecnico = incidenteDetalle.getIncidente().getTecnico().getEspecialidades();
        List<Especialidad> especialidadesProblema = incidenteDetalle.getProblema().getEspecialidades();
        return new HashSet<>(especialidadesTecnico).containsAll(especialidadesProblema);
    }
}
