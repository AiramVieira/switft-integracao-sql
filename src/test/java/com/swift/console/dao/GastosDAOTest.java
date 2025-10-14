package com.swift.console.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class GastosDAOTest {

    @Test
    void testCriarGastosDAO() {
        GastosDAO gastosDAO = new GastosDAO();
        assertNotNull(gastosDAO);
    }

    @Test
    void testGastosDAONaoEhNulo() {
        GastosDAO gastosDAO = new GastosDAO();
        assertNotNull(gastosDAO, "GastosDAO não deve ser nulo");
    }
}
