package com.projectoFinalMotorsport.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectoFinalMotorsport.demo.model.Auto;
import com.projectoFinalMotorsport.demo.model.Equipo;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    Auto findByModelo(String nombre);
}
