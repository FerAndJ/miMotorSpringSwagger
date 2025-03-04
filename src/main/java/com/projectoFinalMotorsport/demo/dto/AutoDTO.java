package com.projectoFinalMotorsport.demo.dto;

import lombok.AllArgsConstructor;
import com.projectoFinalMotorsport.demo.model.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoDTO {
      
    private Long id;
    
    private String modelo;
    
    private String marca;
    
    private String motor;

    private Integer potencia;

    private Integer torque;

    private Integer peso;

    private Integer cargaAerodinamica;

    private Boolean esHibrido;

    private String piloto;

    private String equipo;
}
