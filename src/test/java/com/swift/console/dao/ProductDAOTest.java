package com.swift.console.dao;

import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Product;
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
class ProductDAOTest {

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

    private ProductDAO productDAO;

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

            productDAO = new ProductDAO();
            List<Product> products = productDAO.findAll();

            assertNotNull(products);
            assertTrue(products.isEmpty());
        }
    }

    @Test
    void testFindById_ProdutoEncontrado() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true);
            when(resultSet.getInt("id")).thenReturn(1);
            when(resultSet.getString("nome")).thenReturn("Notebook");
            when(resultSet.getInt("fk_categoria_id")).thenReturn(5);
            when(resultSet.getFloat("preco")).thenReturn(2500.00f);
            when(resultSet.getInt("estoque")).thenReturn(10);
            when(resultSet.getString("imagem")).thenReturn("notebook.jpg");
            when(resultSet.getString("descricao")).thenReturn("Notebook Dell");

            productDAO = new ProductDAO();
            Optional<Product> product = productDAO.findById(1);

            assertTrue(product.isPresent());
            assertEquals("Notebook", product.get().getNome());
            assertEquals(2500.00f, product.get().getPreco(), 0.01);
        }
    }

    @Test
    void testFindById_ProdutoNaoEncontrado() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            productDAO = new ProductDAO();
            Optional<Product> product = productDAO.findById(999);

            assertFalse(product.isPresent());
        }
    }

    @Test
    void testSearchByNome_EncontraProdutos() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true, false);
            when(resultSet.getInt("id")).thenReturn(1);
            when(resultSet.getString("nome")).thenReturn("Mouse Gamer");
            when(resultSet.getInt("fk_categoria_id")).thenReturn(3);
            when(resultSet.getFloat("preco")).thenReturn(150.00f);
            when(resultSet.getInt("estoque")).thenReturn(50);
            when(resultSet.getString("imagem")).thenReturn("mouse.jpg");
            when(resultSet.getString("descricao")).thenReturn("Mouse RGB");

            productDAO = new ProductDAO();
            List<Product> products = productDAO.searchByNome("Mouse");

            assertNotNull(products);
            assertEquals(1, products.size());
            assertEquals("Mouse Gamer", products.get(0).getNome());
        }
    }

    @Test
    void testFindByCategoria_RetornaProdutos() throws SQLException {
        try (MockedStatic<FactoryManager> mockedFactory = mockStatic(FactoryManager.class)) {
            mockedFactory.when(FactoryManager::getInstance).thenReturn(factoryManager);
            
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true, true, false);
            when(resultSet.getInt("id")).thenReturn(1, 2);
            when(resultSet.getString("nome")).thenReturn("Notebook", "Mouse");
            when(resultSet.getInt("fk_categoria_id")).thenReturn(5, 5);
            when(resultSet.getFloat("preco")).thenReturn(2500.00f, 150.00f);
            when(resultSet.getInt("estoque")).thenReturn(10, 50);

            productDAO = new ProductDAO();
            List<Product> products = productDAO.findByCategoria(5);

            assertNotNull(products);
            assertEquals(2, products.size());
            assertEquals(5, products.get(0).getCategoriaId());
            assertEquals(5, products.get(1).getCategoriaId());
        }
    }
}

