package com.example.rentaautos.Entity;


public class Seguro {
    private String empresaAseguradora;
    private Double coberturaMax;
    private Double tarifa;

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
