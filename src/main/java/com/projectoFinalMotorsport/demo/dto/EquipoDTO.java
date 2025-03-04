package com.projectoFinalMotorsport.demo.dto;

import java.util.List;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    
    private Long id;
    
    private String nombre;

    private String nacionalidad;

    private Integer victoriasTotales;

    private String sede;

    private Boolean esMotorista;

    private List<String> pilotos;

    private List<String> autos;

    
}