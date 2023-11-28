package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Especialidad;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Tecnico;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.EspecialidadService;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.TecnicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class TecnicoController {
    @Autowired
    private TecnicoService tecnicoService;
    private EspecialidadService especialidadService;

    @PostMapping("/guardarTecnico")
    public Tecnico guardarTecnico(@RequestBody Tecnico tecnico){
        List<Especialidad> especialidadList = new ArrayList<>();
        for(Especialidad especialidad : tecnico.getEspecialidades()){
            if(!especialidadList.contains(especialidadService.obtenerEspecialidadPorId(especialidad.getId()))){
                especialidadList.add(especialidadService.obtenerEspecialidadPorId(especialidad.getId()));
            }
        }
        tecnico.setEspecialidades(especialidadList);
        return this.tecnicoService.guardar(tecnico);
    }

    @DeleteMapping("/eliminarTecnico/{id}")
    public ResponseEntity<String> borrarTecnicoPorId(@PathVariable Integer id){
        if(tecnicoService.eliminarTecnicoPorId(id)){
            return ResponseEntity.ok("Tecnico eliminado correctamente");
        }
        return ResponseEntity.status(404).body("Tecnico no encontrado");
    }


    @GetMapping("/tecnicos/{id}")
    public Tecnico obtenerTecnicoPorId(@PathVariable Integer id){
        return tecnicoService.obtenerTecnicoPorId(id);
    }
    @GetMapping("/tecnicos")
    public List<Tecnico> obtenerTecnicos(){
        return tecnicoService.obtenerTecnicos();
    }

}
