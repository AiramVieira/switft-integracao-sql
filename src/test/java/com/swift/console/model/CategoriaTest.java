package com.swift.console.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    @Test
    void testCriarCategoriaVazia() {
        Categoria categoria = new Categoria();
        assertNotNull(categoria);
        assertNull(categoria.getCdCategoria());
        assertNull(categoria.getNmCategoria());
        assertNull(categoria.getTpCategoria());
    }

    @Test
    void testCriarCategoriaComParametros() {
        Categoria categoria = new Categoria(1, "Alimentação", "D");
        assertEquals(1, categoria.getCdCategoria());
        assertEquals("Alimentação", categoria.getNmCategoria());
        assertEquals("D", categoria.getTpCategoria());
    }

    @Test
    void testSettersAndGetters() {
        Categoria categoria = new Categoria();
        categoria.setCdCategoria(10);
        categoria.setNmCategoria("Salário");
        categoria.setTpCategoria("R");
        
        assertEquals(10, categoria.getCdCategoria());
        assertEquals("Salário", categoria.getNmCategoria());
        assertEquals("R", categoria.getTpCategoria());
    }

    @Test
    void testToString() {
        Categoria categoria = new Categoria(5, "Transporte", "D");
        String expected = "Categoria{cdCategoria=5, nmCategoria='Transporte', tpCategoria='D'}";
        assertEquals(expected, categoria.toString());
    }

    @Test
    void testModificarNome() {
        Categoria categoria = new Categoria(1, "Saúde", "D");
        categoria.setNmCategoria("Saúde e Bem-estar");
        assertEquals("Saúde e Bem-estar", categoria.getNmCategoria());
    }
}
