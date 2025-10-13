package com.swift.console.model;

import java.math.BigDecimal;
import java.util.Date;

public class Recebimentos {
    private Integer cdRecebimento;
    private Integer cdUsuario;
    private Integer cdCategoria;
    private String nmRecebimento;
    private Date dtRecebimento;
    private BigDecimal vlRecebimento;
    private String dsRecebimento;

    public Recebimentos() {
    }

    public Recebimentos(Integer cdRecebimento, Integer cdUsuario, Integer cdCategoria, String nmRecebimento,
                        Date dtRecebimento, BigDecimal vlRecebimento, String dsRecebimento) {
        this.cdRecebimento = cdRecebimento;
        this.cdUsuario = cdUsuario;
        this.cdCategoria = cdCategoria;
        this.nmRecebimento = nmRecebimento;
        this.dtRecebimento = dtRecebimento;
        this.vlRecebimento = vlRecebimento;
        this.dsRecebimento = dsRecebimento;
    }

    public Integer getCdRecebimento() {
        return cdRecebimento;
    }

    public void setCdRecebimento(Integer cdRecebimento) {
        this.cdRecebimento = cdRecebimento;
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

    public String getNmRecebimento() {
        return nmRecebimento;
    }

    public void setNmRecebimento(String nmRecebimento) {
        this.nmRecebimento = nmRecebimento;
    }

    public Date getDtRecebimento() {
        return dtRecebimento;
    }

    public void setDtRecebimento(Date dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    }

    public BigDecimal getVlRecebimento() {
        return vlRecebimento;
    }

    public void setVlRecebimento(BigDecimal vlRecebimento) {
        this.vlRecebimento = vlRecebimento;
    }

    public String getDsRecebimento() {
        return dsRecebimento;
    }

    public void setDsRecebimento(String dsRecebimento) {
        this.dsRecebimento = dsRecebimento;
    }

    @Override
    public String toString() {
        return "Recebimentos{" +
                "cdRecebimento=" + cdRecebimento +
                ", cdUsuario=" + cdUsuario +
                ", cdCategoria=" + cdCategoria +
                ", nmRecebimento='" + nmRecebimento + '\'' +
                ", dtRecebimento=" + dtRecebimento +
                ", vlRecebimento=" + vlRecebimento +
                ", dsRecebimento='" + dsRecebimento + '\'' +
                '}';
    }
}

