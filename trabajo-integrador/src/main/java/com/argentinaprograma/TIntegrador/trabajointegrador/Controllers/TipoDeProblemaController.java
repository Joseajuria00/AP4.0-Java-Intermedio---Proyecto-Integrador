package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Especialidad;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.TipoDeProblema;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.EspecialidadService;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.TipoDeProblemaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class TipoDeProblemaController {
    @Autowired
    private TipoDeProblemaService tipoDeProblemaService;
    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping("/guardarProblema")
    public TipoDeProblema guardarTipoDeProblema(@RequestBody TipoDeProblema tipoDeProblema){
        List<Especialidad> especialidadList = new ArrayList<>();
        for(Especialidad especialidad : tipoDeProblema.getEspecialidades()){
            if(!especialidadList.contains(especialidadService.obtenerEspecialidadPorId(especialidad.getId()))){
                especialidadList.add(especialidadService.obtenerEspecialidadPorId(especialidad.getId()));
            }
        }
        tipoDeProblema.setEspecialidades(especialidadList);
        return this.tipoDeProblemaService.guardar(tipoDeProblema);
    }
    @DeleteMapping("/eliminarProblema/{id}")
    public ResponseEntity<String> borrarProblemaPorId(@PathVariable Integer id){
        if(tipoDeProblemaService.eliminarTipoDeProblemaPorId(id)){
            return ResponseEntity.ok("Tipo de problema eliminado correctamente");
        }
        return ResponseEntity.status(404).body("Tipo de problema no encontrado");
    }

    @GetMapping("/problemas/{id}")
    public TipoDeProblema obtenerTipoDeProblemaPorId(@PathVariable Integer id){
        return tipoDeProblemaService.obtenerTipoDeProblemaPorId(id);
    }
    @GetMapping("/problemas")
    public List<TipoDeProblema> obtenerTipoDeProblemas(){
        return tipoDeProblemaService.obtenerTipoDeProblemas();
    }
}
