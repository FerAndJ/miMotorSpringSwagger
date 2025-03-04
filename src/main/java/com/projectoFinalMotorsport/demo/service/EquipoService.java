package com.projectoFinalMotorsport.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }
}
