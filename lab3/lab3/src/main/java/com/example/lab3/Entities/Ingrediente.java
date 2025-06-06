package com.example.lab3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idingrediente", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @OneToMany(mappedBy = "idingrediente")
    private List<RecetaIngrediente> recetaIngredientes;

}