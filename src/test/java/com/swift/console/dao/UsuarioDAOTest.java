package com.swift.console.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class UsuarioDAOTest {

    @Test
    void testCriarUsuarioDAO() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        assertNotNull(usuarioDAO);
    }

    @Test
    void testUsuarioDAONaoEhNulo() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        assertNotNull(usuarioDAO, "UsuarioDAO não deve ser nulo");
    }
}
