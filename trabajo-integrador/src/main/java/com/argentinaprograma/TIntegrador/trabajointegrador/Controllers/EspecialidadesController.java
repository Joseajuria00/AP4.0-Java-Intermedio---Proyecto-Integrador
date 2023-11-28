package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Especialidad;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.EspecialidadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class EspecialidadesController {
    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping("/guardarEspecialidad")
    public Especialidad guardarEspecialidad(@RequestBody Especialidad especialidad){
        return this.especialidadService.guardar(especialidad);
    }
    @DeleteMapping("/eliminarEspecialidad/{id}")
    public ResponseEntity<String> borrarClientePorId(@PathVariable Integer id){
        if(especialidadService.eliminarEspecialidadPorId(id)){
            return ResponseEntity.ok("Especialidad eliminada correctamente");
        }
        return ResponseEntity.status(404).body("Especialidad no encontrada");
    }

    @GetMapping("especialidades/{id}")
    public Especialidad obtenerEspecialidadPorId(@PathVariable Integer id){
        return especialidadService.obtenerEspecialidadPorId(id);
    }
    @GetMapping("/especialidades")
    public List<Especialidad> obtenerEspecialidades(){
        return especialidadService.obtenerEspecialidades();
    }
}
