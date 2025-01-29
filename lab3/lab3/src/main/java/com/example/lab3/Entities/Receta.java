package com.example.lab3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "receta")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreceta", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idcategoria", nullable = false)
    private Categoria idcategoria;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Lob
    @Column(name = "instrucciones")
    private String instrucciones;

    @Column(name = "dificultad")
    private Integer dificultad;

    @Column(name = "tiempo_preparacion")
    private LocalTime tiempoPreparacion;

}