package com.projectoFinalMotorsport.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectoFinalMotorsport.demo.mappers.PilotoMapper;
import com.projectoFinalMotorsport.demo.model.Auto;
import com.projectoFinalMotorsport.demo.model.Carrera;
import com.projectoFinalMotorsport.demo.model.Categoria;
import com.projectoFinalMotorsport.demo.model.Equipo;
import com.projectoFinalMotorsport.demo.model.Piloto;
import com.projectoFinalMotorsport.demo.repository.AutoRepository;
import com.projectoFinalMotorsport.demo.repository.CarreraRepository;
import com.projectoFinalMotorsport.demo.repository.EquipoRepository;
import com.projectoFinalMotorsport.demo.repository.PilotoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.projectoFinalMotorsport.demo.dto.PilotoDTO;;

@Service
public class PilotoService {

    @Autowired
    private PilotoRepository pilotoRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

     public Piloto save(Piloto piloto) {
        return pilotoRepository.save(piloto);
    }

    @Transactional
    public List<PilotoDTO> listarPilotos() {
        return pilotoRepository.findAll().stream().map(PilotoMapper::toDto).toList();
    }

    @Transactional
    public PilotoDTO obtenerPilotoDTOPorId(Long id) {
        Piloto piloto = pilotoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Piloto no encontrado"));

        return PilotoMapper.toDto(piloto);
    }

    @Transactional
    public PilotoDTO agregarPiloto(PilotoDTO pilotoDTO) {
        
        
        List<Carrera> carreras = carreraRepository.findAll().stream()
            .filter(carrera -> pilotoDTO.getCarreras().contains(carrera.getNombre()))
            .toList();

        Auto autoPiloto = (pilotoDTO.getAuto() != null)
                ? autoRepository.findByModelo(pilotoDTO.getAuto())
                : null;

        Equipo equipoPiloto = (pilotoDTO.getEquipo() != null)
                ? equipoRepository.findByNombre(pilotoDTO.getEquipo())
                : null;
        
        Piloto piloto = Piloto.builder()
            .nombre(pilotoDTO.getNombre())
            .peso(pilotoDTO.getPeso())
            .numero(pilotoDTO.getNumero())
            .nacionalidad(pilotoDTO.getNacionalidad())
            .categoria(Categoria.valueOf(pilotoDTO.getCategoria()))
            .equipo(equipoPiloto)
            .carreras(carreras)
            .auto(autoPiloto)
            .build();

        
        Piloto pilotoCreado = this.save(piloto);
        return  PilotoMapper.toDto(pilotoCreado);
        
        /* 
        Piloto piloto = this.save(PilotoMapper.toEntity(pilotoDTO, equipoRepository, carreraRepository, autoRepository));
        return pilotoDTO;
        */
    }

    @Transactional
    public PilotoDTO actualizarPiloto(Long id, PilotoDTO pilotoDTO) {
        Piloto piloto = pilotoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Piloto con id: " + id + "no encontrado"));
            
        List<Carrera> carrerasPiloto = carreraRepository.findAll().stream()
            .filter(carrera -> pilotoDTO.getCarreras().stream()
                .anyMatch(carreraPilotoDTO -> carreraPilotoDTO.equals(carrera.getNombre())))
                .collect(Collectors.toList());
        
    if(carrerasPiloto.isEmpty()) {
        throw new IllegalArgumentException("No se encontraron carreras");
    }

    Auto autoPiloto = (pilotoDTO.getAuto() != null)
                ? autoRepository.findByModelo(pilotoDTO.getAuto())
                : null;

    Equipo equipoPiloto = (pilotoDTO.getEquipo() != null)
                ? equipoRepository.findByNombre(pilotoDTO.getEquipo())
                : null;
    
    piloto.setNombre(pilotoDTO.getNombre());
    piloto.setPeso(pilotoDTO.getPeso());
    piloto.setNumero(pilotoDTO.getNumero());
    piloto.setNacionalidad(pilotoDTO.getNacionalidad());
    piloto.setCategoria(Categoria.valueOf(pilotoDTO.getCategoria()));
    piloto.setEquipo(equipoPiloto);
    piloto.setCarreras(carrerasPiloto);
    piloto.setAuto(autoPiloto);

    return PilotoMapper.toDto(pilotoRepository.save(piloto));
    
    }

    @Transactional
    public void eliminarPiloto(Long id) {
        
        Piloto piloto = pilotoRepository.findById(id).orElseThrow(() -> new RuntimeException("Piloto no encontrado"));
        
        
        for(Carrera carrera : piloto.getCarreras()) {
            carrera.getPilotos().remove(piloto);
        }

        piloto.getCarreras().clear();

        
        if(piloto.getAuto() != null) {
            piloto.getAuto().setPiloto(null);
            piloto.setAuto(null);
        }

        //ELIMINO TODAS LAS REFERENCIAS
        
        pilotoRepository.delete(pilotoRepository.findById(id).get());
        System.out.println("Piloto eliminado con exito");
    }
            
}
