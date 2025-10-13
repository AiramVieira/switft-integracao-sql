package com.swift.console.model;

public class TiposInvestimento {
    private Integer cdTipo;
    private String risco;
    private String nmTipo;

    public TiposInvestimento() {
    }

    public TiposInvestimento(Integer cdTipo, String risco, String nmTipo) {
        this.cdTipo = cdTipo;
        this.risco = risco;
        this.nmTipo = nmTipo;
    }

    public Integer getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(Integer cdTipo) {
        this.cdTipo = cdTipo;
    }

    public String getRisco() {
        return risco;
    }

    public void setRisco(String risco) {
        this.risco = risco;
    }

    public String getNmTipo() {
        return nmTipo;
    }

    public void setNmTipo(String nmTipo) {
        this.nmTipo = nmTipo;
    }

    @Override
    public String toString() {
        return "TiposInvestimento{" +
                "cdTipo=" + cdTipo +
                ", risco='" + risco + '\'' +
                ", nmTipo='" + nmTipo + '\'' +
                '}';
    }
}

