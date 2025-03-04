package com.projectoFinalMotorsport.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectoFinalMotorsport.demo.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    Equipo findByNombre(String nombre);
}
