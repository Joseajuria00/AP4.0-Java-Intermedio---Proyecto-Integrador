package com.argentinaprograma.TIntegrador.trabajointegrador.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="incidente")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fechaEstimada;
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private Boolean resuelto;
    private String consideraciones;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_tecnico",
            referencedColumnName = "id")
    private Tecnico tecnico;
}
