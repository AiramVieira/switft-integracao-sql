package com.swift.console.model;

public class Categoria {
    private Integer cdCategoria;
    private String nmCategoria;
    private String tpCategoria;

    public Categoria() {
    }

    public Categoria(Integer cdCategoria, String nmCategoria, String tpCategoria) {
        this.cdCategoria = cdCategoria;
        this.nmCategoria = nmCategoria;
        this.tpCategoria = tpCategoria;
    }

    public Integer getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(Integer cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public String getNmCategoria() {
        return nmCategoria;
    }

    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }

    public String getTpCategoria() {
        return tpCategoria;
    }

    public void setTpCategoria(String tpCategoria) {
        this.tpCategoria = tpCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "cdCategoria=" + cdCategoria +
                ", nmCategoria='" + nmCategoria + '\'' +
                ", tpCategoria='" + tpCategoria + '\'' +
                '}';
    }
}
