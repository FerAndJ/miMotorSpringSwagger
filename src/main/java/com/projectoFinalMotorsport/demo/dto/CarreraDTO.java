package com.projectoFinalMotorsport.demo.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.projectoFinalMotorsport.demo.model.*;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
    
    private Long id;
    
    private String nombre;

    private String ubicacion;

    private String autodromo;

    private LocalDateTime horario;

    private Integer kmVuelta;

    private Integer numerosVueltas;

    private Integer temperaturaPromedio;

    private String gamaNeumaticos;

    private Double probabilidadSafetyCar;

    private List<String> pilotos;

}

