package com.example.lab3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidad_medida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idunidad_medida", nullable = false)
    private Integer id;

    @Column(name = "nombre_unidad", length = 45)
    private String nombreUnidad;

    @Column(name = "simbolo", length = 10)
    private String simbolo;

}