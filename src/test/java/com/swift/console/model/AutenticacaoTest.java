package com.swift.console.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class AutenticacaoTest {

    @Test
    void testCriarAutenticacaoVazia() {
        Autenticacao autenticacao = new Autenticacao();
        assertNotNull(autenticacao);
        assertNull(autenticacao.getCdAutenticacao());
        assertNull(autenticacao.getCdUsuario());
        assertNull(autenticacao.getEmail());
        assertNull(autenticacao.getSenha());
        assertNull(autenticacao.getStatusConta());
    }

    @Test
    void testCriarAutenticacaoComParametros() {
        Autenticacao autenticacao = new Autenticacao(1, 10, "user@test.com", "senha123", "ATIVO");
        assertEquals(1, autenticacao.getCdAutenticacao());
        assertEquals(10, autenticacao.getCdUsuario());
        assertEquals("user@test.com", autenticacao.getEmail());
        assertEquals("senha123", autenticacao.getSenha());
        assertEquals("ATIVO", autenticacao.getStatusConta());
    }

    @Test
    void testSettersAndGetters() {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setCdAutenticacao(5);
        autenticacao.setCdUsuario(20);
        autenticacao.setEmail("test@example.com");
        autenticacao.setSenha("pass456");
        autenticacao.setStatusConta("INATIVO");
        
        assertEquals(5, autenticacao.getCdAutenticacao());
        assertEquals(20, autenticacao.getCdUsuario());
        assertEquals("test@example.com", autenticacao.getEmail());
        assertEquals("pass456", autenticacao.getSenha());
        assertEquals("INATIVO", autenticacao.getStatusConta());
    }

    @Test
    void testToString() {
        Autenticacao autenticacao = new Autenticacao(1, 10, "user@test.com", "senha123", "ATIVO");
        String result = autenticacao.toString();
        assertTrue(result.contains("cdAutenticacao=1"));
        assertTrue(result.contains("email='user@test.com'"));
        assertTrue(result.contains("senha='***'"));
        assertTrue(result.contains("statusConta='ATIVO'"));
    }
}

