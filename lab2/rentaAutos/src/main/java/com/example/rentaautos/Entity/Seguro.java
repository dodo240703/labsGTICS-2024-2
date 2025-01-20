package com.example.rentaautos.Entity;


public class Seguro {
    private Integer id;
    private String empresaAseguradora;
    private Double coberturaMax;
    private Double tarifa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresaAseguradora() {
        return empresaAseguradora;
    }

    public void setEmpresaAseguradora(String empresaAseguradora) {
        this.empresaAseguradora = empresaAseguradora;
    }

    public Double getCoberturaMax() {
        return coberturaMax;
    }

    public void setCoberturaMax(Double coberturaMax) {
        this.coberturaMax = coberturaMax;
    }

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }
}
