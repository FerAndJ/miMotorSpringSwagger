package com.projectoFinalMotorsport.demo.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectoFinalMotorsport.demo.dto.CarreraDTO;
import com.projectoFinalMotorsport.demo.model.*;
import com.projectoFinalMotorsport.demo.repository.PilotoRepository;

@Component
public class CarreraMapper {

    public static CarreraDTO toDto(Carrera carrera) {
        if (carrera == null) {
            return null;
        }

        return CarreraDTO.builder()
                .id(carrera.getId())
                .nombre(carrera.getNombre())
                .ubicacion(carrera.getUbicacion())
                .autodromo(carrera.getAutodromo())
                .horario(carrera.getHorario())
                .kmVuelta(carrera.getKmVuelta())
                .numerosVueltas(carrera.getNumerosVueltas())
                .temperaturaPromedio(carrera.getTemperaturaPromedio())
                .gamaNeumaticos(carrera.getGamaNeumaticos().name())
                .probabilidadSafetyCar(carrera.getProbabilidadSafetyCar())
                .pilotos(carrera.getPilotos() != null
                        ? carrera.getPilotos().stream().map(Piloto::getNombre).toList()
                        : List.of())
                .build();
    }

    public static Carrera toEntity(CarreraDTO carreraDTO, PilotoRepository pilotoRepository) {
        if (carreraDTO == null) {
            return null;
        }

        List<Piloto> pilotosEntidad = carreraDTO.getPilotos() != null
        ? pilotoRepository.findAll().stream()
          .filter(piloto -> carreraDTO.getPilotos().contains(piloto.getNombre()))
          .toList()
        : List.of();

        return Carrera.builder()
                .id(carreraDTO.getId())
                .nombre(carreraDTO.getNombre())
                .ubicacion(carreraDTO.getUbicacion())
                .autodromo(carreraDTO.getAutodromo())
                .horario(carreraDTO.getHorario())
                .kmVuelta(carreraDTO.getKmVuelta())
                .numerosVueltas(carreraDTO.getNumerosVueltas())
                .temperaturaPromedio(carreraDTO.getTemperaturaPromedio())
                .gamaNeumaticos(GamaNeumaticos.valueOf(carreraDTO.getGamaNeumaticos()))
                .probabilidadSafetyCar(carreraDTO.getProbabilidadSafetyCar())
                .pilotos(pilotosEntidad)
                .build();

    }
}