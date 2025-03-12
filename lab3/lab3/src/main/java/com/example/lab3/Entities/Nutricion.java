package com.example.lab3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nutricion")
public class Nutricion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnutricion", nullable = false)
    private Integer id;

    @Column(name = "calorias")
    private Double calorias;

    @Column(name = "grasas")
    private Double grasas;

    @Column(name = "proteinas")
    private Double proteinas;

    @Column(name = "carbohidratos")
    private Double carbohidratos;

    @Column(name = "fibra")
    private Double fibra;

    @ManyToOne
    @JoinColumn(name = "idreceta", nullable = false)
    private Receta receta;


}