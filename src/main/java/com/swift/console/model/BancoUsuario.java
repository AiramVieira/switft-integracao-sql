package com.swift.console.model;

public class BancoUsuario {
    private Integer cdUsuario;
    private Integer nrAgencia;
    private Long nrConta;
    private Long cpf;
    private Integer cdBanco;

    public BancoUsuario() {
    }

    public BancoUsuario(Integer cdUsuario, Integer nrAgencia, Long nrConta, Long cpf, Integer cdBanco) {
        this.cdUsuario = cdUsuario;
        this.nrAgencia = nrAgencia;
        this.nrConta = nrConta;
        this.cpf = cpf;
        this.cdBanco = cdBanco;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getNrAgencia() {
        return nrAgencia;
    }

    public void setNrAgencia(Integer nrAgencia) {
        this.nrAgencia = nrAgencia;
    }

    public Long getNrConta() {
        return nrConta;
    }

    public void setNrConta(Long nrConta) {
        this.nrConta = nrConta;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Integer getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(Integer cdBanco) {
        this.cdBanco = cdBanco;
    }

    @Override
    public String toString() {
        return "BancoUsuario{" +
                "cdUsuario=" + cdUsuario +
                ", nrAgencia=" + nrAgencia +
                ", nrConta=" + nrConta +
                ", cpf=" + cpf +
                ", cdBanco=" + cdBanco +
                '}';
    }
}

