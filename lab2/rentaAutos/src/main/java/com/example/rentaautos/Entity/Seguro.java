package com.example.rentaautos.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Seguro {
    private Integer idSeguro;
    private String empresaAseguradora;
    private Double coberturaMax;
    private Double tarifa;
}
