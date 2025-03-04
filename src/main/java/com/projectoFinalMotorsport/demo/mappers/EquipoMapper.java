
package com.projectoFinalMotorsport.demo.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectoFinalMotorsport.demo.dto.EquipoDTO;
import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.repository.AutoRepository;
import com.projectoFinalMotorsport.demo.repository.PilotoRepository;
import com.projectoFinalMotorsport.demo.model.*;

@Component
public class EquipoMapper {

    public static EquipoDTO toDto(Equipo equipo) {
        if(equipo == null) {
            return null;
        }

        return EquipoDTO.builder()
                .id(equipo.getId())
                .nombre(equipo.getNombre())
                .nacionalidad(equipo.getNacionalidad())
                .victoriasTotales(equipo.getVictoriasTotales())
                .sede(equipo.getSede())
                .esMotorista(equipo.getEsMotorista())
                .pilotos(equipo.getPilotos() != null
                         ? equipo.getPilotos().stream().map(Piloto::getNombre).toList()
                         : List.of())
                .autos(equipo.getAutos() != null
                        ? equipo.getAutos().stream().map(Auto::getModelo).toList()
                        : List.of())
                .build();

        }

        public static Equipo toEntity(EquipoDTO equipoDTO, PilotoRepository pilotoRepository, AutoRepository autoRepository) {
                return Equipo.builder()
                .id(equipoDTO.getId())
                .nombre(equipoDTO.getNombre())
                .nacionalidad(equipoDTO.getNacionalidad())
                .victoriasTotales(equipoDTO.getVictoriasTotales())
                .sede(equipoDTO.getSede())
                .esMotorista(equipoDTO.getEsMotorista())
                .pilotos(equipoDTO.getPilotos() != null
                ? pilotoRepository.findAll().stream()
                  .filter(piloto -> equipoDTO.getPilotos().contains(piloto.getNombre()))
                  .toList() 
                 : List.of())
                .autos(equipoDTO.getAutos() != null
                       ? autoRepository.findAll().stream()
                         .filter(auto -> equipoDTO.getAutos().contains(auto.getModelo()))
                         .toList() 
                        : List.of())      
                .build();       
        }



    }
