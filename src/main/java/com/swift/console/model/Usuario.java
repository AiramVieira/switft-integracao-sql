package com.swift.console.model;

import java.math.BigDecimal;
import java.util.Date;

public class Usuario {
    private Integer cdUsuario;
    private Integer cdAutenticacao;
    private String nmUsuario;
    private Date dtNascimento;
    private String nrTelefone;
    private String ativo;
    private BigDecimal vlSaldo;

    public Usuario() {
    }

    public Usuario(Integer cdUsuario, Integer cdAutenticacao, String nmUsuario, Date dtNascimento, 
                   String nrTelefone, String ativo, BigDecimal vlSaldo) {
        this.cdUsuario = cdUsuario;
        this.cdAutenticacao = cdAutenticacao;
        this.nmUsuario = nmUsuario;
        this.dtNascimento = dtNascimento;
        this.nrTelefone = nrTelefone;
        this.ativo = ativo;
        this.vlSaldo = vlSaldo;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getCdAutenticacao() {
        return cdAutenticacao;
    }

    public void setCdAutenticacao(Integer cdAutenticacao) {
        this.cdAutenticacao = cdAutenticacao;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getVlSaldo() {
        return vlSaldo;
    }

    public void setVlSaldo(BigDecimal vlSaldo) {
        this.vlSaldo = vlSaldo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cdUsuario=" + cdUsuario +
                ", cdAutenticacao=" + cdAutenticacao +
                ", nmUsuario='" + nmUsuario + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", nrTelefone='" + nrTelefone + '\'' +
                ", ativo='" + ativo + '\'' +
                ", vlSaldo=" + vlSaldo +
                '}';
    }
}
