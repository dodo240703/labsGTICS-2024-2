package com.example.lab3.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "receta_equipamiento")
public class RecetaEquipamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreceta_equipamiento", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idreceta", nullable = false)
    private Receta idreceta;

    @ManyToOne
    @JoinColumn(name = "idequipamiento", nullable = false)
    private Equipamiento idequipamiento;

}