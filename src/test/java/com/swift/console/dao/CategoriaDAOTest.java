package com.swift.console.dao;

import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaDAOTest {

    @Mock
    private FactoryManager factoryManager;

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private CallableStatement callableStatement;

    @Mock
    private ResultSet resultSet;

    private CategoriaDAO categoriaDAO;

    @BeforeEach
    void setUp() throws SQLException {
        when(factoryManager.getConnection()).thenReturn(connection);
        doNothing().when(factoryManager).closeConnection(connection);
    }

    @Test
    void testFindAll_RetornaListaVazia() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.findAll();

            assertNotNull(categorias);
            assertTrue(categorias.isEmpty());
            verify(statement).close();
            verify(resultSet).close();
        }
    }

    @Test
    void testFindAll_RetornaListaComCategorias() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true, true, false);
            when(resultSet.getInt("id")).thenReturn(1, 2);
            when(resultSet.getString("descricao")).thenReturn("Eletrônicos", "Livros");

            categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.findAll();

            assertNotNull(categorias);
            assertEquals(2, categorias.size());
            assertEquals("Eletrônicos", categorias.get(0).getDescricao());
            assertEquals("Livros", categorias.get(1).getDescricao());
        }
    }

    @Test
    void testFindById_CategoriaEncontrada() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true);
            when(resultSet.getInt("id")).thenReturn(1);
            when(resultSet.getString("descricao")).thenReturn("Eletrônicos");

            categoriaDAO = new CategoriaDAO();
            Optional<Categoria> categoria = categoriaDAO.findById(1);

            assertTrue(categoria.isPresent());
            assertEquals(1, categoria.get().getId());
            assertEquals("Eletrônicos", categoria.get().getDescricao());
        }
    }

    @Test
    void testFindById_CategoriaNaoEncontrada() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            categoriaDAO = new CategoriaDAO();
            Optional<Categoria> categoria = categoriaDAO.findById(999);

            assertFalse(categoria.isPresent());
        }
    }

    @Test
    void testSave_InserirCategoria() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareCall(anyString())).thenReturn(callableStatement);
            when(callableStatement.getInt(2)).thenReturn(10);

            categoriaDAO = new CategoriaDAO();
            Categoria categoria = new Categoria();
            categoria.setDescricao("Nova Categoria");

            Categoria salva = categoriaDAO.save(categoria);

            assertNotNull(salva);
            assertEquals(10, salva.getId());
            assertEquals("Nova Categoria", salva.getDescricao());
            verify(callableStatement).setString(1, "Nova Categoria");
            verify(callableStatement).execute();
        }
    }
}

