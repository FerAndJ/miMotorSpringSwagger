package com.projectoFinalMotorsport.demo.mappers;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.stereotype.Component;

import com.projectoFinalMotorsport.demo.dto.PilotoDTO;
import com.projectoFinalMotorsport.demo.model.Auto;
import com.projectoFinalMotorsport.demo.model.Carrera;
import com.projectoFinalMotorsport.demo.model.Categoria;
import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.model.Piloto;
import com.projectoFinalMotorsport.demo.repository.AutoRepository;
import com.projectoFinalMotorsport.demo.repository.CarreraRepository;
import com.projectoFinalMotorsport.demo.repository.EquipoRepository;


@Component
public class PilotoMapper {

    public static PilotoDTO toDto(Piloto piloto) {
        if (piloto == null) {
            return null;
        }

        return PilotoDTO.builder()
                .id(piloto.getId())
                .nombre(piloto.getNombre())
                .peso(piloto.getPeso())
                .numero(piloto.getNumero())
                .nacionalidad(piloto.getNacionalidad())
                .categoria(piloto.getCategoria().name())
                .equipo(piloto.getEquipo().getNombre())
                .carreras(piloto.getCarreras() != null
                        ? piloto.getCarreras().stream().map(Carrera::getNombre).toList()
                        : List.of())
                .auto(piloto.getAuto() != null ? piloto.getAuto().getModelo() : null)
                .build();
    }

    public static Piloto toEntity(PilotoDTO pilotoDTO, EquipoRepository equipoRepository, CarreraRepository carreraRepository, AutoRepository autoRepository) {
        if (pilotoDTO == null) {
            return null;
        }

        
        Equipo equipoToEntity = (pilotoDTO.getEquipo() != null)
                ? equipoRepository.findByNombre(pilotoDTO.getEquipo())
                : null;

        Auto autotoEntity = (pilotoDTO.getAuto() != null)
                ? autoRepository.findByModelo(pilotoDTO.getAuto())
                : null;
        
        
        Piloto piloto = Piloto.builder()
                .id(pilotoDTO.getId())
                .nombre(pilotoDTO.getNombre())
                .peso(pilotoDTO.getPeso())
                .numero(pilotoDTO.getNumero())
                .nacionalidad(pilotoDTO.getNacionalidad())
                .categoria(Categoria.valueOf(pilotoDTO.getCategoria()))
                .equipo(equipoToEntity)
                .carreras(pilotoDTO.getCarreras() != null
                ? carreraRepository.findAll().stream()
                  .filter(carrera -> pilotoDTO.getCarreras().contains(carrera.getNombre()))
                  .toList()
                : List.of())
                .auto(autotoEntity)
                .build();

        return piloto;
    }
}


