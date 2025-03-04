package com.projectoFinalMotorsport.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectoFinalMotorsport.demo.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository <Carrera, Long> {

}
