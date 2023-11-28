package com.argentinaprograma.TIntegrador.trabajointegrador.repositories;

import com.argentinaprograma.TIntegrador.trabajointegrador.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
