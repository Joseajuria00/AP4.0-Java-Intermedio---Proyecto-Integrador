package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Servicio;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.ServicioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @PostMapping("/guardarServicio")
    public Servicio guardarServicio(@RequestBody Servicio servicio){
        return this.servicioService.guardar(servicio);
    }
    @DeleteMapping("/eliminarServicio/{id}")
    public ResponseEntity<String> borrarServicioPorId(@PathVariable Integer id){
        if(servicioService.eliminarServicioPorId(id)){
            return ResponseEntity.ok("Servicio eliminado correctamente");
        }
        return ResponseEntity.status(404).body("Servicio no encontrado");
    }

    @GetMapping("/servicios/{id}")
    public Servicio obtenerServicioPorId(@PathVariable Integer id){
        return servicioService.obtenerServicioPorId(id);
    }
    @GetMapping("/servicios")
    public List<Servicio> obtenerServicios(){
        return servicioService.obtenerServicios();
    }
}
