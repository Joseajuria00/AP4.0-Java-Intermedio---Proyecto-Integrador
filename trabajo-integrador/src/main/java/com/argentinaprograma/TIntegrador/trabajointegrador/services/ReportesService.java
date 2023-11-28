package com.argentinaprograma.TIntegrador.trabajointegrador.services;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Especialidad;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Incidente;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.IncidenteDetalle;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Tecnico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReportesService {
    @Autowired
    IncidenteService incidenteService;
    @Autowired
    IncidenteDetalleService incidenteDetalleService;
    @Autowired
    EspecialidadService especialidadService;
    //--------- REPORTE A ---------
    public List<Incidente> obtenerIncidentesResueltosEnNDiasPorTecnico(Integer id_tecnico, Integer dias){
        return obtenerIncidentesResueltos(this.incidenteService.obtenerIncidentes(), id_tecnico, dias);
    }
    //--------- REPORTE B ---------
    public List<Incidente> obtenerIncidentesResueltosEnEspecialidadEnNDiasPorTecnico(Integer id_tecnico, Integer id_especialidad, Integer dias){
        List<Incidente> incidentesDeEspecialidad = obtenerIncidentesPorEspecialidad(especialidadService.obtenerEspecialidadPorId(id_especialidad));
        return obtenerIncidentesResueltos(incidentesDeEspecialidad, id_tecnico, dias);
    }
    public List<Incidente> obtenerIncidentesResueltos(List<Incidente> incidenteList, Integer id_tecnico, Integer dias){
        List<Incidente> incidentes = new ArrayList<>();
        for(Incidente incidente : incidenteList){
            if(incidente.getTecnico().getId() == id_tecnico && incidente.getResuelto()){
                if(!incidente.getFechaHasta().isBefore(LocalDate.now().atStartOfDay().minusDays(dias))) incidentes.add(incidente);
            }
        }
        return incidentes;
    }
    public List<Incidente> obtenerIncidentesPorEspecialidad(Especialidad especialidad){
        List<Incidente> incidenteList = new ArrayList<>();
        for(IncidenteDetalle incidenteDetalle : incidenteDetalleService.obtenerIncidentesDetalles()){
            if(incidenteDetalle.getProblema().getEspecialidades().contains(especialidad)){
                if(!incidenteList.contains(incidenteDetalle.getIncidente())) incidenteList.add(incidenteDetalle.getIncidente());
            }
        }
        return incidenteList;
    }
    //--------- REPORTE C ---------
    public Duration obtenerPromedioTecnico(Tecnico tecnico){
        Duration duracion = Duration.ZERO;
        int cantidad=0;
        for (Incidente incidente : incidenteService.obtenerIncidentes()){
            if(incidente.getTecnico().equals(tecnico) && incidente.getResuelto()){
                duracion = duracion.plus(Duration.between(incidente.getFechaDesde(), incidente.getFechaHasta()));
                cantidad++;
            }
        }
        if(duracion.isZero()) return null;
        return duracion.dividedBy(cantidad);
    }

}
