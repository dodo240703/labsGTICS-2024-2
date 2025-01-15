package com.example.rentaautos.Entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Auto {
    private Integer idAuto;
    private String modelo;
    private String color;
    private int kilometraje;
    private Sede sede;
    private Double costoDia;

}
