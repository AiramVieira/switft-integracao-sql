package com.swift.console.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class AutenticacaoDAOTest {

    @Test
    void testCriarAutenticacaoDAO() {
        AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO();
        assertNotNull(autenticacaoDAO);
    }

    @Test
    void testAutenticacaoDAONaoEhNulo() {
        AutenticacaoDAO autenticacaoDAO = new AutenticacaoDAO();
        assertNotNull(autenticacaoDAO, "AutenticacaoDAO não deve ser nulo");
    }
}

