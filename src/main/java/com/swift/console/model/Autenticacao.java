package com.swift.console.model;

public class Autenticacao {
    private Integer cdAutenticacao;
    private Integer cdUsuario;
    private String email;
    private String senha;
    private String statusConta;

    public Autenticacao() {
    }

    public Autenticacao(Integer cdAutenticacao, Integer cdUsuario, String email, String senha, String statusConta) {
        this.cdAutenticacao = cdAutenticacao;
        this.cdUsuario = cdUsuario;
        this.email = email;
        this.senha = senha;
        this.statusConta = statusConta;
    }

    public Integer getCdAutenticacao() {
        return cdAutenticacao;
    }

    public void setCdAutenticacao(Integer cdAutenticacao) {
        this.cdAutenticacao = cdAutenticacao;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(String statusConta) {
        this.statusConta = statusConta;
    }

    @Override
    public String toString() {
        return "Autenticacao{" +
                "cdAutenticacao=" + cdAutenticacao +
                ", cdUsuario=" + cdUsuario +
                ", email='" + email + '\'' +
                ", senha='***'" +
                ", statusConta='" + statusConta + '\'' +
                '}';
    }
}

