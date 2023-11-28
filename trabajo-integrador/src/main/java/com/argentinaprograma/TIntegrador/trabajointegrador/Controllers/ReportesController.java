package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Tecnico;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/soporte/reportes")
@AllArgsConstructor
public class ReportesController {
    @Autowired
    private ReportesService reportesService;
    @Autowired
    private TecnicoService tecnicoService;
    //--------- REPORTE A ---------
    @GetMapping("dias/{dias}")
    public List<Tecnico> obtenerTecnicoMasIncidentesResueltosEnNDias(@PathVariable int dias){
        List<Tecnico> mejorTecnicoList = new ArrayList<>();
        int cantidadMejor=0;
        int cantidadTecnico;
        for(Tecnico tecnico : tecnicoService.obtenerTecnicos()){
            cantidadTecnico = reportesService.obtenerIncidentesResueltosEnNDiasPorTecnico(tecnico.getId(), dias).size();
            if (cantidadTecnico!=0){
                if(cantidadTecnico>cantidadMejor){
                    mejorTecnicoList.clear();
                    mejorTecnicoList.add(tecnico);
                    cantidadMejor = cantidadTecnico;
                } else if (cantidadTecnico==cantidadMejor) {
                    mejorTecnicoList.add(tecnico);
                }
            }
        }
        return mejorTecnicoList;
    }
    //--------- REPORTE B ---------
    @GetMapping("especialidad/{id_especialidad}/dias/{dias}")
    public List<Tecnico> obtenerTecnicoMasIncidentesResueltosEnEspecialidadEnNDias(@PathVariable("id_especialidad") int id_especialidad, @PathVariable("dias") int dias){
        List<Tecnico> mejorTecnicoList = new ArrayList<>();
        int cantidadMejor=0;
        int cantidadTecnico;
        for(Tecnico tecnico : tecnicoService.obtenerTecnicos()){
            cantidadTecnico = reportesService.obtenerIncidentesResueltosEnEspecialidadEnNDiasPorTecnico(tecnico.getId(), id_especialidad, dias).size();
            if(cantidadTecnico!=0){
                System.out.println("ID TECNICO: " + tecnico.getId() + " + INCIDENTES" + cantidadTecnico);
                if(cantidadTecnico>cantidadMejor){
                    mejorTecnicoList.clear();
                    mejorTecnicoList.add(tecnico);
                    cantidadMejor = cantidadTecnico;
                } else if (cantidadTecnico==cantidadMejor) {
                    mejorTecnicoList.add(tecnico);
                }
            }
        }
        return mejorTecnicoList;
    }
    //--------- REPORTE C ---------
    @GetMapping("mas-rapido")
    public List<Tecnico> obtenerTecnicoMasRapido(){
        Duration mejorPromedio = Duration.ZERO;
        Duration tiempoPromedio;
        List<Tecnico> mejorTecnicoList = new ArrayList<>();
        for(Tecnico tecnico : tecnicoService.obtenerTecnicos()){
            tiempoPromedio = reportesService.obtenerPromedioTecnico(tecnico);
            if (tiempoPromedio != null) {
                if (mejorPromedio.isZero() || tiempoPromedio.compareTo(mejorPromedio) < 0) {
                    mejorPromedio = tiempoPromedio;
                    mejorTecnicoList.clear();
                    mejorTecnicoList.add(tecnico);
                } else if (tiempoPromedio.compareTo(mejorPromedio)==0) mejorTecnicoList.add(tecnico);
            }
        }
        return mejorTecnicoList;
    }

}
