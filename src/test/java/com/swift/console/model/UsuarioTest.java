package com.swift.console.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testCriarUsuarioVazio() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario);
        assertNull(usuario.getId());
        assertNull(usuario.getNome());
        assertNull(usuario.getSobrenome());
    }

    @Test
    void testCriarUsuarioComParametros() {
        Usuario usuario = new Usuario(1, "João", "Silva", 10, "11999999999", "PF");
        assertEquals(1, usuario.getId());
        assertEquals("João", usuario.getNome());
        assertEquals("Silva", usuario.getSobrenome());
        assertEquals(10, usuario.getEnderecoId());
        assertEquals("11999999999", usuario.getTelephone());
        assertEquals("PF", usuario.getTipo());
    }

    @Test
    void testSettersAndGetters() {
        Usuario usuario = new Usuario();
        usuario.setId(5);
        usuario.setNome("Maria");
        usuario.setSobrenome("Oliveira");
        usuario.setEnderecoId(20);
        usuario.setTelephone("11888888888");
        usuario.setTipo("PJ");
        
        assertEquals(5, usuario.getId());
        assertEquals("Maria", usuario.getNome());
        assertEquals("Oliveira", usuario.getSobrenome());
        assertEquals(20, usuario.getEnderecoId());
        assertEquals("11888888888", usuario.getTelephone());
        assertEquals("PJ", usuario.getTipo());
    }

    @Test
    void testNomeCompleto() {
        Usuario usuario = new Usuario(1, "Carlos", "Santos", null, "11777777777", "PF");
        String nomeCompleto = usuario.getNome() + " " + usuario.getSobrenome();
        assertEquals("Carlos Santos", nomeCompleto);
    }

    @Test
    void testTipoPessoaFisica() {
        Usuario usuario = new Usuario();
        usuario.setTipo("PF");
        assertEquals("PF", usuario.getTipo());
    }

    @Test
    void testTipoPessoaJuridica() {
        Usuario usuario = new Usuario();
        usuario.setTipo("PJ");
        assertEquals("PJ", usuario.getTipo());
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario(1, "Ana", "Costa", 5, "11666666666", "PF");
        String result = usuario.toString();
        assertTrue(result.contains("Ana"));
        assertTrue(result.contains("Costa"));
    }
}

