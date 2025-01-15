package com.example.rentaautos.Entity;


public class Auto {
    private String modelo;
    private String color;
    private int kilometraje;
    private String sede;
    private Double costoDia;

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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Double getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(Double costoDia) {
        this.costoDia = costoDia;
    }
}
