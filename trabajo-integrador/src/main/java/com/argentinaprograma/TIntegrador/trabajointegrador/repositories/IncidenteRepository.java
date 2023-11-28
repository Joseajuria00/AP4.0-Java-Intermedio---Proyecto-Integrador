package com.argentinaprograma.TIntegrador.trabajointegrador.repositories;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {
}
