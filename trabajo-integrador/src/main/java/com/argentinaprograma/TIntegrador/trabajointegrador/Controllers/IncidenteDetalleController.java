package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.IncidenteDetalle;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class IncidenteDetalleController {
    @Autowired
    private IncidenteDetalleService incidenteDetalleService;
    @Autowired
    private IncidenteService incidenteService;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private TipoDeProblemaService tipoDeProblemaService;

    @PostMapping("/guardarIncidenteDetalle")
    public IncidenteDetalle guardarIncidenteDetalle(@RequestBody IncidenteDetalle incidenteDetalle){
        incidenteDetalle.setIncidente(incidenteService.obtenerIncidentePorId(incidenteDetalle.getIncidente().getId()));
        incidenteDetalle.setServicio(servicioService.obtenerServicioPorId(incidenteDetalle.getServicio().getId()));
        incidenteDetalle.setProblema(tipoDeProblemaService.obtenerTipoDeProblemaPorId(incidenteDetalle.getProblema().getId()));
        if(incidenteDetalleService.tecnicoHabilitado(incidenteDetalle)){
            return this.incidenteDetalleService.guardar(incidenteDetalle);
        }
        return null;
    }
    @DeleteMapping("/eliminarIncidenteDetalle/{id}")
    public ResponseEntity<String> borrarClientePorId(@PathVariable Integer id){
        if(incidenteDetalleService.eliminarIncidenteDetallePorId(id)){
            return ResponseEntity.ok("Detalle de incidente eliminado correctamente");
        }
        return ResponseEntity.status(404).body("Detalle de incidente no encontrado");
    }

    @GetMapping("/incidentesDetalles/{id}")
    public IncidenteDetalle obtenerIncidenteDetallePorId(@PathVariable Integer id){
        return incidenteDetalleService.obtenerIncidenteDetallePorId(id);
    }
    @GetMapping("/incidentesDetalles")
    public List<IncidenteDetalle> obtenerIncidentesDetalles(){
        return incidenteDetalleService.obtenerIncidentesDetalles();
    }
}
