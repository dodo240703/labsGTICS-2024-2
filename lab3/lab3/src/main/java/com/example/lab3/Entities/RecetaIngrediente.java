package com.example.lab3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "receta_ingrediente")
public class RecetaIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta_ingrediente", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idreceta", nullable = false)
    private Receta idreceta;

    @ManyToOne
    @JoinColumn(name = "idingrediente", nullable = false)
    private Ingrediente idingrediente;

    @ManyToOne
    @JoinColumn(name = "idunidad_medida", nullable = false)
    private UnidadMedida idunidadMedida;

    @Column(name = "cantidad")
    private Double cantidad;

}