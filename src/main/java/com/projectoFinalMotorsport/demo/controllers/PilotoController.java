package com.projectoFinalMotorsport.demo.controllers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectoFinalMotorsport.demo.dto.PilotoDTO;
import com.projectoFinalMotorsport.demo.mappers.PilotoMapper;
import com.projectoFinalMotorsport.demo.model.Piloto;
import com.projectoFinalMotorsport.demo.repository.AutoRepository;
import com.projectoFinalMotorsport.demo.repository.CarreraRepository;
import com.projectoFinalMotorsport.demo.repository.EquipoRepository;
import com.projectoFinalMotorsport.demo.service.PilotoService;
import com.projectoFinalMotorsport.demo.utils.ApiResponse;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;


@RestController
@RequestMapping("/motorSpring")
public class PilotoController {

    @Autowired
    private PilotoService pilotoService;

    @Autowired
    private PilotoMapper pilotoMapper;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private AutoRepository autoRepository;

    //GET TODOS LOS PILOTOS
    //localhost:8080/motorSpring/pilotos/
    
    @GetMapping("/pilotos")
    @Operation(summary = "Obtenemos todos los registros de los pilotos iniciales desde la base h2", description = "Obtenemos registros almacenados manualmente en la aplicacion")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", 
            description = "Successful Operation", 
            content = @Content(schema = @Schema(
                implementation = PilotoDTO.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Registers not found",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        )))
    })
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok().body(new ApiResponse("Pilotos obtenidos con exito", pilotoService.listarPilotos(),"N/A"));
    }

    @GetMapping("/pilotosSort")
    @Operation(summary = "Obtenemos todos los registros de los pilotos iniciales desde la base h2, ordenados por peso", description = "Obtenemos registros almacenados manualmente en la aplicacion")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", 
            description = "Successful Operation", 
            content = @Content(schema = @Schema(
                implementation = PilotoDTO.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Registers not found",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        )))
    })
   
    public ResponseEntity<?> listarPorPeso(@RequestParam(defaultValue = "desc") String order) {
    return ResponseEntity.ok().body(new ApiResponse("Pilotos obtenidos con éxito", 
        pilotoService.listarPilotos().stream()
            .sorted((p1, p2) -> order.equals("asc")
                ? p1.getPeso().compareTo(p2.getPeso())  
                : p2.getPeso().compareTo(p1.getPeso())) 
            
        ,"N/A"));
    }

    @GetMapping("/filterCategoria")
    @Operation(summary = "Filtramos a los pilotos por categoria", description = "Filtramos a los pilotos por categoria a la que pertenezcan, por ejemplo IMSA")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", 
            description = "Successful Operation", 
            content = @Content(schema = @Schema(
                implementation = PilotoDTO.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Registers not found",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        )))
    })
   
    public ResponseEntity<?> filterByCategoria(@RequestParam String category) {
    return ResponseEntity.ok().body(new ApiResponse("Pilotos obtenidos con éxito", 
        pilotoService.listarPilotos().stream()
        .filter(piloto -> piloto.getCategoria().equalsIgnoreCase(category))
        .collect(Collectors.toList())
            
            
        ,"N/A")); 

        //Collector es para transformar el string en una lista, sin eso toList() hace que sea inmutable
    }

    
    //GET PILOTO POR ID (sobre carga del metodo)
    // localhost:8080/motorSpring/piloto/2 para obtener el de id 2, hamilton
    
    @GetMapping("/piloto/{id}")
    @Operation(summary = "Obtenemos el registro de un piloto de la base h2", description = "Obtenemos el registro del piloto por id almacenado manualmente en la aplicacion")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", 
            description = "Successful Operation", 
            content = @Content(schema = @Schema(
                implementation = PilotoDTO.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "Register by id not found",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        )))
    })
    public ResponseEntity<?> listar(@PathVariable Long id) {
        return ResponseEntity.ok().body(new ApiResponse("Piloto obtenido con exito", pilotoService.obtenerPilotoDTOPorId(id),"N/A"));
    }
   
    //POST PILOTO
    // localhost:8080/motorSpring/crearPiloto  para crear un nuevo piloto en el body (piloto 3 en DemoApp)

    @PostMapping("/crearPiloto")
    @Operation(summary = "Creamos un nuevo registro en la base h2", description = "Creamos el registro del piloto ingresado mediante un json, el endpoint valida que no se ingrese un nombre o numero de piloto repetido")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", 
            description = "Successful Operation", 
            content = @Content(schema = @Schema(
                implementation = PilotoDTO.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Couldnt create the driver",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        ))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "409",
            description = "The driver already exists",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        )))
    })
    public ResponseEntity<?> agregar(@RequestBody PilotoDTO pilotoDTO) {
        try {
            ApiResponse responseCreacionPiloto = pilotoService.agregarPiloto(pilotoDTO);
            return ResponseEntity.ok().body(responseCreacionPiloto);
            
        }
        catch(Exception e) {
            ApiResponse responseCreacionPiloto = pilotoService.agregarPiloto(pilotoDTO);
            return ResponseEntity.badRequest().body(responseCreacionPiloto);
        }
    }
    //PUT PILOTO
    // localhost:8080/motorSpring/piloto/2 para modificar a hamilton

    @PutMapping("/modificarPiloto/{id}")
    @Operation(summary = "Modifica un registro en la base h2", description = "Modificamos el registro de un piloto mediante un json, el endpoint valida que no se ingrese un numero o nombre de piloto que ya se encuentre registrado")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200", 
            description = "Successful Operation", 
            content = @Content(schema = @Schema(
                implementation = PilotoDTO.class))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Couldnt modify the driver",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        ))),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "409",
            description = "The driver already exists",
            content = @Content(schema = @Schema(
                implementation = ApiResponse.class
        )))
    })
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody PilotoDTO pilotoDTO) {
        try {
            ApiResponse responseActualizacionPiloto = pilotoService.actualizarPiloto(id, pilotoDTO);
            return ResponseEntity.ok().body(responseActualizacionPiloto);

        }
        catch(Exception e) {
            ApiResponse responseActualizacionPiloto = pilotoService.actualizarPiloto(id, pilotoDTO);
            return ResponseEntity.badRequest().body(responseActualizacionPiloto);
        }
    }
    //DELETE PILOTO
    //localhost:8080/motorSpring/eliminarPiloto/3 para eliminar a kobayashi luego de agregarlo

     @DeleteMapping("/eliminarPiloto/{id}")
     @Operation(summary = "Borrar un piloto de la DB", description = "Borra un piloto segun el ID ingresado")
     @ApiResponses(value = {
         @io.swagger.v3.oas.annotations.responses.ApiResponse(
         responseCode = "200", 
         description = "Driver deleted",
         content = @Content(schema = @Schema(
                 implementation = ApiResponse.class))),
     @io.swagger.v3.oas.annotations.responses.ApiResponse(
         responseCode = "404", 
         description = "Driver not found",
         content = @Content(schema = @Schema(
             implementation = ApiResponse.class 
             )))
         })
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            PilotoDTO pilotoABorrar = this.pilotoService.obtenerPilotoDTOPorId(id);
            pilotoService.eliminarPiloto(id);
            return ResponseEntity.ok().body(new ApiResponse("Se elimino el piloto con exito", pilotoABorrar, "N/A"));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Datos invalidos para eliminar", null, e.getMessage()));
        }
    }


}
