package com.swift.console.model;

import java.math.BigDecimal;
import java.util.Date;

public class Gastos {
    private Integer cdGasto;
    private Integer cdUsuario;
    private Integer cdCategoria;
    private String nmGasto;
    private Date dtGasto;
    private BigDecimal vlGasto;
    private String dsGasto;

    public Gastos() {
    }

    public Gastos(Integer cdGasto, Integer cdUsuario, Integer cdCategoria, String nmGasto, 
                  Date dtGasto, BigDecimal vlGasto, String dsGasto) {
        this.cdGasto = cdGasto;
        this.cdUsuario = cdUsuario;
        this.cdCategoria = cdCategoria;
        this.nmGasto = nmGasto;
        this.dtGasto = dtGasto;
        this.vlGasto = vlGasto;
        this.dsGasto = dsGasto;
    }

    public Integer getCdGasto() {
        return cdGasto;
    }

    public void setCdGasto(Integer cdGasto) {
        this.cdGasto = cdGasto;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(Integer cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public String getNmGasto() {
        return nmGasto;
    }

    public void setNmGasto(String nmGasto) {
        this.nmGasto = nmGasto;
    }

    public Date getDtGasto() {
        return dtGasto;
    }

    public void setDtGasto(Date dtGasto) {
        this.dtGasto = dtGasto;
    }

    public BigDecimal getVlGasto() {
        return vlGasto;
    }

    public void setVlGasto(BigDecimal vlGasto) {
        this.vlGasto = vlGasto;
    }

    public String getDsGasto() {
        return dsGasto;
    }

    public void setDsGasto(String dsGasto) {
        this.dsGasto = dsGasto;
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "cdGasto=" + cdGasto +
                ", cdUsuario=" + cdUsuario +
                ", cdCategoria=" + cdCategoria +
                ", nmGasto='" + nmGasto + '\'' +
                ", dtGasto=" + dtGasto +
                ", vlGasto=" + vlGasto +
                ", dsGasto='" + dsGasto + '\'' +
                '}';
    }
}

