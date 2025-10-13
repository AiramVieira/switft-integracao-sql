package com.swift.console.model;

import java.math.BigDecimal;
import java.util.Date;

public class Investimento {
    private Integer cdInvestimento;
    private Integer cdUsuario;
    private Integer cdTipo;
    private BigDecimal vlInvestimento;
    private Date dtInvestimento;
    private BigDecimal rentabilidadeEstimada;
    private Date dtVencimento;

    public Investimento() {
    }

    public Investimento(Integer cdInvestimento, Integer cdUsuario, Integer cdTipo, BigDecimal vlInvestimento,
                        Date dtInvestimento, BigDecimal rentabilidadeEstimada, Date dtVencimento) {
        this.cdInvestimento = cdInvestimento;
        this.cdUsuario = cdUsuario;
        this.cdTipo = cdTipo;
        this.vlInvestimento = vlInvestimento;
        this.dtInvestimento = dtInvestimento;
        this.rentabilidadeEstimada = rentabilidadeEstimada;
        this.dtVencimento = dtVencimento;
    }

    public Integer getCdInvestimento() {
        return cdInvestimento;
    }

    public void setCdInvestimento(Integer cdInvestimento) {
        this.cdInvestimento = cdInvestimento;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getCdTipo() {
        return cdTipo;
    }

    public void setCdTipo(Integer cdTipo) {
        this.cdTipo = cdTipo;
    }

    public BigDecimal getVlInvestimento() {
        return vlInvestimento;
    }

    public void setVlInvestimento(BigDecimal vlInvestimento) {
        this.vlInvestimento = vlInvestimento;
    }

    public Date getDtInvestimento() {
        return dtInvestimento;
    }

    public void setDtInvestimento(Date dtInvestimento) {
        this.dtInvestimento = dtInvestimento;
    }

    public BigDecimal getRentabilidadeEstimada() {
        return rentabilidadeEstimada;
    }

    public void setRentabilidadeEstimada(BigDecimal rentabilidadeEstimada) {
        this.rentabilidadeEstimada = rentabilidadeEstimada;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    @Override
    public String toString() {
        return "Investimento{" +
                "cdInvestimento=" + cdInvestimento +
                ", cdUsuario=" + cdUsuario +
                ", cdTipo=" + cdTipo +
                ", vlInvestimento=" + vlInvestimento +
                ", dtInvestimento=" + dtInvestimento +
                ", rentabilidadeEstimada=" + rentabilidadeEstimada +
                ", dtVencimento=" + dtVencimento +
                '}';
    }
}

