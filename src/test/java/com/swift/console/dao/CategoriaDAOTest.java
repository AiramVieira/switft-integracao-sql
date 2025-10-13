package com.swift.console.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class CategoriaDAOTest {

    @Test
    void testCriarCategoriaDAO() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        assertNotNull(categoriaDAO);
    }

    @Test
    void testCategoriaDAONaoEhNulo() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        assertNotNull(categoriaDAO, "CategoriaDAO não deve ser nulo");
    }
}
