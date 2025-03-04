package com.projectoFinalMotorsport.demo.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="EQUIPO")
public class Equipo {

    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String nacionalidad;

    private Integer victoriasTotales;

    private String sede;

    private Boolean esMotorista;

    
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Piloto> pilotos;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Auto> autos;

    public Equipo() {
    }

    

}
