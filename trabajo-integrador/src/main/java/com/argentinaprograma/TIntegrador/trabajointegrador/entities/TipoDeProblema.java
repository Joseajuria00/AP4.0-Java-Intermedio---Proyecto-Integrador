package com.argentinaprograma.TIntegrador.trabajointegrador.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tipo_de_problema")
public class TipoDeProblema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private Integer horasEstimadas;
    private Integer horasMaximas;
    private Boolean complejo;
    @ManyToMany
    @JoinTable(
            name = "problema_especialidad",
            joinColumns = @JoinColumn(name = "id_tipo_de_problema"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades;
}
