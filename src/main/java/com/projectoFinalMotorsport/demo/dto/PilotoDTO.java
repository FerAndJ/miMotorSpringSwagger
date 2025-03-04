package com.projectoFinalMotorsport.demo.dto;

import java.util.List;

import com.projectoFinalMotorsport.demo.model.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PilotoDTO {
    private Long id;
    private String nombre;
    
    private Integer peso;
    
    private Integer numero;
    
    private String nacionalidad;
    
    private String categoria;

    private String equipo;

    private List<String> carreras;

    private String auto;

}
