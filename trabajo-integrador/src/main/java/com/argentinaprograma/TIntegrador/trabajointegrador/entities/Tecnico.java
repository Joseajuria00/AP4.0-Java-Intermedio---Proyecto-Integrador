package com.argentinaprograma.TIntegrador.trabajointegrador.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tecnico")
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String email;
    private String numTel;
    @ManyToMany
    @JoinTable(
            name = "tecnico_especialidad",
            joinColumns = @JoinColumn(name = "id_Tecnico"),
            inverseJoinColumns = @JoinColumn(name = "id_Especialidad")
    )
    private List<Especialidad> especialidades;
}
