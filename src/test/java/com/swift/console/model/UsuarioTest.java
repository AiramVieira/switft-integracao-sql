package com.swift.console.model;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    void testCriarUsuarioVazio() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario);
        assertNull(usuario.getCdUsuario());
        assertNull(usuario.getNmUsuario());
    }

    @Test
    void testCriarUsuarioComParametros() {
        Date data = new Date();
        BigDecimal saldo = new BigDecimal("1000.00");
        Usuario usuario = new Usuario(1, 5, "João Silva", data, "11999999999", "S", saldo);
        
        assertEquals(1, usuario.getCdUsuario());
        assertEquals(5, usuario.getCdAutenticacao());
        assertEquals("João Silva", usuario.getNmUsuario());
        assertEquals(data, usuario.getDtNascimento());
        assertEquals("11999999999", usuario.getNrTelefone());
        assertEquals("S", usuario.getAtivo());
        assertEquals(saldo, usuario.getVlSaldo());
    }

    @Test
    void testSettersAndGetters() {
        Usuario usuario = new Usuario();
        Date data = new Date();
        BigDecimal saldo = new BigDecimal("500.00");
        
        usuario.setCdUsuario(10);
        usuario.setCdAutenticacao(15);
        usuario.setNmUsuario("Maria Santos");
        usuario.setDtNascimento(data);
        usuario.setNrTelefone("11988888888");
        usuario.setAtivo("N");
        usuario.setVlSaldo(saldo);
        
        assertEquals(10, usuario.getCdUsuario());
        assertEquals(15, usuario.getCdAutenticacao());
        assertEquals("Maria Santos", usuario.getNmUsuario());
        assertEquals(data, usuario.getDtNascimento());
        assertEquals("11988888888", usuario.getNrTelefone());
        assertEquals("N", usuario.getAtivo());
        assertEquals(saldo, usuario.getVlSaldo());
    }

    @Test
    void testToString() {
        Date data = new Date();
        BigDecimal saldo = new BigDecimal("2000.00");
        Usuario usuario = new Usuario(1, 5, "Pedro Oliveira", data, "11977777777", "S", saldo);
        String result = usuario.toString();
        
        assertTrue(result.contains("cdUsuario=1"));
        assertTrue(result.contains("nmUsuario='Pedro Oliveira'"));
        assertTrue(result.contains("ativo='S'"));
    }
}
