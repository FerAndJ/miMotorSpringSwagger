package com.projectoFinalMotorsport.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="PILOTO")
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    private Integer peso;
    
    private Integer numero;
    
    private String nacionalidad;
    
    private Categoria categoria;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EQUIPO_ID")
    private Equipo equipo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PILOTO_CARRERA", joinColumns = @JoinColumn(name = "PILOTO_ID"), inverseJoinColumns = @JoinColumn(name = "CARRERA_ID"))
    private List<Carrera> carreras = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "AUTO_ID", referencedColumnName = "id")
    private Auto auto;


    public Piloto() {
    }

    
}
