package com.projectoFinalMotorsport.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectoFinalMotorsport.demo.model.Auto;
import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.repository.AutoRepository;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

     public Auto save(Auto auto) {
        return autoRepository.save(auto);
    }
}
