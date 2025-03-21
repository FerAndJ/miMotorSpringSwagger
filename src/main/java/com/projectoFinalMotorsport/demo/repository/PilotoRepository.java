package com.projectoFinalMotorsport.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.model.Piloto;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    Optional<Piloto> findByNombre(String piloto);

    Optional<Piloto> findByNumero(Integer piloto);

    List<Piloto> findAllByNombre(String nombre);

    List<Piloto> findAllByNumero(Integer numero);

}
