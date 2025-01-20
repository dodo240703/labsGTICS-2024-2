package com.example.rentaautos.Entity;


public class Auto {
    private Integer id;
    private String modelo;
    private String color;
    private int kilometraje;
    private Integer sede;
    private Double costoDia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public Integer getSede() {
        return sede;
    }

    public void setSede(Integer sede) {
        this.sede = sede;
    }

    public Double getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(Double costoDia) {
        this.costoDia = costoDia;
    }
}
