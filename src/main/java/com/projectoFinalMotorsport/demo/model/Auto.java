package com.projectoFinalMotorsport.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="AUTO")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String modelo;
    
    private String marca;
    
    @Enumerated(EnumType.STRING)
    private Motor motor;

    private Integer potencia;

    private Integer torque;

    private Integer peso;

    private Integer cargaAerodinamica;

    private Boolean esHibrido;

    @OneToOne(mappedBy = "auto")
    private Piloto piloto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EQUIPO_ID")
    private Equipo equipo;

    public Auto() {
    }

    
}