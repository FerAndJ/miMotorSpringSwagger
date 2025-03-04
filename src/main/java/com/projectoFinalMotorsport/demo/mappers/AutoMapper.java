package com.projectoFinalMotorsport.demo.mappers;

import org.springframework.stereotype.Component;

import com.projectoFinalMotorsport.demo.dto.AutoDTO;
import com.projectoFinalMotorsport.demo.model.*;
import com.projectoFinalMotorsport.demo.repository.EquipoRepository;
import com.projectoFinalMotorsport.demo.repository.PilotoRepository;

@Component
public class AutoMapper {

    public static AutoDTO toDto(Auto auto) {
        if (auto == null) {
            return null;
        }

        return AutoDTO.builder()
                .id(auto.getId())
                .modelo(auto.getModelo())
                .marca(auto.getMarca())
                .motor(auto.getMotor().name())
                .potencia(auto.getPotencia())
                .torque(auto.getTorque())
                .peso(auto.getPeso())
                .cargaAerodinamica(auto.getCargaAerodinamica())
                .esHibrido(auto.getEsHibrido())
                .piloto(auto.getPiloto().getNombre())
                .equipo(auto.getEquipo().getNombre())
                .build();
    }

    public static Auto toEntity(AutoDTO autoDTO, PilotoRepository pilotoRepository, EquipoRepository equipoRepository) {
        if (autoDTO == null) {
            return null;
        }

        
        Piloto pilotoToEntity = autoDTO.getPiloto() != null
                ? pilotoRepository.findByNombre(autoDTO.getPiloto())
                : null;

        
        Equipo equipoToEntity = (autoDTO.getEquipo() != null)
                ? equipoRepository.findByNombre(autoDTO.getEquipo())
                : null;

        // Construir la entidad Auto
        Auto auto = Auto.builder()
                .id(autoDTO.getId())
                .modelo(autoDTO.getModelo())
                .marca(autoDTO.getMarca())
                .motor(Motor.valueOf(autoDTO.getMotor()))
                .potencia(autoDTO.getPotencia())
                .torque(autoDTO.getTorque())
                .peso(autoDTO.getPeso())
                .cargaAerodinamica(autoDTO.getCargaAerodinamica())
                .esHibrido(autoDTO.getEsHibrido())
                .piloto(pilotoToEntity)
                .equipo(equipoToEntity)
                .build();

        return auto;
    }
}