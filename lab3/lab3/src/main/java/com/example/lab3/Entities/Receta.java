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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Integer getDificultad() {
        return dificultad;
    }

    public void setDificultad(Integer dificultad) {
        this.dificultad = dificultad;
    }

    public LocalTime getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(LocalTime tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}