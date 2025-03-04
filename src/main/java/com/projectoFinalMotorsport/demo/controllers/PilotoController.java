package com.projectoFinalMotorsport.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectoFinalMotorsport.demo.dto.PilotoDTO;
import com.projectoFinalMotorsport.demo.mappers.PilotoMapper;
import com.projectoFinalMotorsport.demo.model.Piloto;
import com.projectoFinalMotorsport.demo.repository.AutoRepository;
import com.projectoFinalMotorsport.demo.repository.CarreraRepository;
import com.projectoFinalMotorsport.demo.repository.EquipoRepository;
import com.projectoFinalMotorsport.demo.service.PilotoService;
import com.projectoFinalMotorsport.demo.utils.ApiResponse;

@RestController
@RequestMapping("/pilotos")
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
    //localhost:8080/pilotos/
    
    @GetMapping
    public ResponseEntity<List<PilotoDTO>> listar() {
        return ResponseEntity.ok().body(pilotoService.listarPilotos());
    }

    
    //GET PILOTO POR ID (sobre carga del metodo)
    // localhost:8080/pilotos/piloto/2 para obtener el de id 2, hamilton
    
    @GetMapping("/piloto/{id}")
    public ResponseEntity<PilotoDTO> listar(@PathVariable Long id) {
        return ResponseEntity.ok().body(pilotoService.obtenerPorId(id));
    }

    //POST PILOTO
    // localhost:8080/pilotos/crearPiloto  para crear un nuevo piloto en el body (piloto 3 en DemoApp)

    @PostMapping("/crearPiloto")
    public ResponseEntity<?> agregar(@RequestBody PilotoDTO pilotoDTO) {
        try {
            PilotoDTO pilotoCreado = pilotoService.agregarPiloto(pilotoDTO);
            return ResponseEntity.ok().body(new ApiResponse("Piloto agregado con exito", pilotoCreado, "Sin errores"));
            
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Datos invalidos para agregar piloto", null, e.getMessage()));
        }
    }


    //PUT PILOTO
    // localhost:8080/pilotos/piloto/2 para modificar a hamilton

    @PutMapping("/modificarPiloto/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody PilotoDTO pilotoDTO) {
        try {
            PilotoDTO pilotoModificado = pilotoService.actualizarPiloto(id, pilotoDTO);
            return ResponseEntity.ok().body(new ApiResponse("Actualizacion exitosa del piloto", pilotoModificado, "Sin errores"));

        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Datos invalidos para modificar", null, e.getMessage()));
        }
    }
    //DELETE PILOTO
    //localhost:8080/pilotos/eliminarPiloto/3 para eliminar a kobayashi luego de agreggarlo

     @DeleteMapping("/eliminarPiloto/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            pilotoService.eliminarPiloto(id);
            return ResponseEntity.ok().body(new ApiResponse("Se elimino el piloto con exito", null, "Sin errores"));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Datos invalidos para eliminar", null, e.getMessage()));
        }
    }


}
