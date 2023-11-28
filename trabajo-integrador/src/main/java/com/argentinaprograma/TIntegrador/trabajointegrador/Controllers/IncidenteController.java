package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Incidente;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.ClienteService;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.IncidenteService;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.TecnicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class IncidenteController {
    @Autowired
    private IncidenteService incidenteService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TecnicoService tecnicoService;

    @PostMapping("/guardarIncidente")
    public Incidente guardarIncidente(@RequestBody Incidente incidente){
        incidente.setCliente(clienteService.obtenerClientePorId(incidente.getCliente().getId()));
        incidente.setTecnico(tecnicoService.obtenerTecnicoPorId(incidente.getTecnico().getId()));
        return this.incidenteService.guardar(incidente);
    }
    @DeleteMapping("/eliminarIncidente/{id}")
    public ResponseEntity<String> borrarIncidentePorId(@PathVariable Integer id){
        if(incidenteService.eliminarIncidentePorId(id)){
            return ResponseEntity.ok("Incidente eliminado correctamente");
        }
        return ResponseEntity.status(404).body("Incidente no encontrado");
    }

    @GetMapping("/incidentes/{id}")
    public Incidente obtenerIncidentePorId(@PathVariable Integer id){
        return incidenteService.obtenerIncidentePorId(id);
    }
    @GetMapping("/incidentes")
    public List<Incidente> obtenerIncidentes(){
        return incidenteService.obtenerIncidentes();
    }
}
