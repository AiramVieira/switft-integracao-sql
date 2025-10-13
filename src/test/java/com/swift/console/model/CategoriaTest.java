package com.swift.console.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void testCriarCategoriaVazia() {
        Categoria categoria = new Categoria();
        assertNotNull(categoria);
        assertNull(categoria.getId());
        assertNull(categoria.getDescricao());
    }

    @Test
    void testCriarCategoriaComParametros() {
        Categoria categoria = new Categoria(1, "Eletrônicos");
        assertEquals(1, categoria.getId());
        assertEquals("Eletrônicos", categoria.getDescricao());
    }

    @Test
    void testSettersAndGetters() {
        Categoria categoria = new Categoria();
        categoria.setId(10);
        categoria.setDescricao("Alimentos");
        
        assertEquals(10, categoria.getId());
        assertEquals("Alimentos", categoria.getDescricao());
    }

    @Test
    void testToString() {
        Categoria categoria = new Categoria(5, "Livros");
        String expected = "Categoria{id=5, descricao='Livros'}";
        assertEquals(expected, categoria.toString());
    }

    @Test
    void testModificarDescricao() {
        Categoria categoria = new Categoria(1, "Móveis");
        categoria.setDescricao("Móveis e Decoração");
        assertEquals("Móveis e Decoração", categoria.getDescricao());
    }
}

