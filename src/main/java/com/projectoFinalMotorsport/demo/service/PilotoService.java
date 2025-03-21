package com.projectoFinalMotorsport.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.projectoFinalMotorsport.demo.utils.ApiResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
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

    @Autowired
    private PilotoMapper pilotoMapper;

    @PersistenceContext
    private EntityManager entityManager;

     public Piloto save(Piloto piloto) {
        return pilotoRepository.save(piloto);
    }

    @Transactional
    public List<PilotoDTO> listarPilotos() {
        return pilotoRepository.findAll().stream().map(pilotoMapper::toDto).toList();
    }

    @Transactional
    public PilotoDTO obtenerPilotoDTOPorId(Long id) {
        Piloto piloto = pilotoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Piloto no encontrado"));

        return pilotoMapper.toDto(piloto);
    }

    @Transactional
    public ApiResponse agregarPiloto(PilotoDTO pilotoDTO) {
        
    try {   
        String isValidName = this.validatePilot(pilotoDTO);
        
        
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
            return  new ApiResponse(HttpStatus.CREATED.name(), pilotoDTO, "Piloto creado con exito");
        
        }   catch(Exception e) {
            return  new ApiResponse(HttpStatus.BAD_REQUEST.name(), pilotoDTO, e.getMessage());
        }
        
        /* 
        Piloto piloto = this.save(PilotoMapper.toEntity(pilotoDTO, equipoRepository, carreraRepository, autoRepository));
        return pilotoDTO;
        */
    }

    private String validatePilot(PilotoDTO pilotoDTO) {
        String pilotoName = pilotoDTO.getNombre();
        String pilotoNumberString = pilotoDTO.getNumero().toString();
        Integer pilotoNumber = pilotoDTO.getNumero();
        

        if(pilotoName == null || pilotoName.isEmpty() || pilotoName.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }

        else if(pilotoNumberString == null || pilotoNumberString.isEmpty() || pilotoNumberString.isBlank())
         {
            throw new IllegalArgumentException("El numero no puede estar vacio");
         }

         else if(nombreOcupado(pilotoName)) {
            throw new IllegalArgumentException("El nombre del piloto ya fue registrado");
         }

         else if(numeroOcupado(pilotoNumber)) {
            throw new IllegalArgumentException("El numero del piloto ya fue registrado");
         }

        return "Piloto valido";
    }

    private Boolean nombreOcupado(String pilotoName) {
        
        return pilotoRepository.findByNombre(pilotoName).isPresent();
    }

    private Boolean numeroOcupado(Integer pilotoNumber) {
        return pilotoRepository.findByNumero(pilotoNumber).isPresent();
    }


    @Transactional
    public ApiResponse actualizarPiloto(Long id, PilotoDTO pilotoDTO) {
        
        try {
        
            String isValidName = this.validatePilot(pilotoDTO);
        
            Piloto piloto = pilotoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Piloto con id: " + id + "no encontrado"));
            
            List<Carrera> carrerasPiloto = carreraRepository.findAll().stream()
                .filter(carrera -> pilotoDTO.getCarreras().stream()
                    .anyMatch(carreraPilotoDTO -> carreraPilotoDTO.equals(carrera.getNombre())))
                    .collect(Collectors.toList());
        
            /* 
            if(carrerasPiloto.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron carreras");
            }
            VALIDACION POR SI SE QUIERE CARGAR A UN PILOTO CON ALGUNA CARRERA*/

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

            Piloto pilotoActualizado = pilotoRepository.save(piloto);
            
            return  new ApiResponse(HttpStatus.CREATED.name(), pilotoDTO, "Piloto actualizado con exito");
        
        }   catch(Exception e) {
                return  new ApiResponse(HttpStatus.BAD_REQUEST.name(), pilotoDTO, e.getMessage());
        }

    
    }

    @Transactional
    public void eliminarPiloto(Long id) {
        
        
        
        Piloto piloto = pilotoRepository.findById(id).orElseThrow(() -> new RuntimeException("Piloto no encontrado"));
        
        
        if(piloto.getCarreras() != null && !piloto.getCarreras().isEmpty()) {
            
            for(Carrera carrera : piloto.getCarreras()) {
                carrera.getPilotos().remove(piloto);
                carreraRepository.save(carrera);
            }
     
            piloto.getCarreras().clear();
        }
        
        if(piloto.getAuto() != null) {
            Auto auto = piloto.getAuto();
            auto.setPiloto(null);
            autoRepository.save(auto);
            piloto.setAuto(null);
        }
        
        
        if(piloto.getEquipo() != null) {
            piloto.setEquipo(null);
        }
        //ELIMINO TODAS LAS REFERENCIAS
        entityManager.flush();
        
        pilotoRepository.delete(piloto);

        
        System.out.println("Piloto eliminado: " + piloto.getNombre()); // Log para confirmar
        
    }
            
}
