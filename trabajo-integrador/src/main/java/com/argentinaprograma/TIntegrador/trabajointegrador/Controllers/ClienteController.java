package com.argentinaprograma.TIntegrador.trabajointegrador.Controllers;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Cliente;
import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Servicio;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.ClienteService;
import com.argentinaprograma.TIntegrador.trabajointegrador.services.ServicioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/soporte")
@AllArgsConstructor
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ServicioService servicioService;

    @PostMapping("/guardarCliente")
    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente){
        List<Servicio> servicioList = new ArrayList<>();
        for(Servicio servicio : cliente.getServicios()){
            if(!servicioList.contains(servicioService.obtenerServicioPorId(servicio.getId()))) {
                servicioList.add(servicioService.obtenerServicioPorId(servicio.getId()));
            }
        }
        cliente.setServicios(servicioList);
        return new ResponseEntity<> (this.clienteService.guardar(cliente), HttpStatus.OK);
    }
    @DeleteMapping("/eliminarCliente/{id}")
    public ResponseEntity<String> borrarClientePorId(@PathVariable Integer id){
        if(clienteService.eliminarClientePorId(id)){
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }
        return ResponseEntity.status(404).body("Cliente no encontrado");
    }
    @GetMapping("/clientes/{id}")
    public Cliente obtenerClientedPorId(@PathVariable Integer id){
        return clienteService.obtenerClientePorId(id);
    }
    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        return clienteService.obtenerClientes();
    }
}
