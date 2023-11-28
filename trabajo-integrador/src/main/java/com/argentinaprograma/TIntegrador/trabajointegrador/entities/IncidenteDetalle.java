package com.argentinaprograma.TIntegrador.trabajointegrador.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="incidente_detalle")
public class IncidenteDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "id_incidente", referencedColumnName = "id")
    private Incidente incidente;
    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id")
    private Servicio servicio;
    @ManyToOne
    @JoinColumn(name = "id_tipoDeProblema", referencedColumnName = "id")
    private TipoDeProblema problema;
}
