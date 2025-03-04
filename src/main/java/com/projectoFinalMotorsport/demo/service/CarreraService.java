package com.projectoFinalMotorsport.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectoFinalMotorsport.demo.model.Carrera;
import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.repository.CarreraRepository;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

     public Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }
}
