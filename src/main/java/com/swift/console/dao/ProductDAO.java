package com.swift.console.dao;

import java.sql.*;
import java.util.*;
import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Product;

public class ProductDAO {

    private FactoryManager factory;
    
    public ProductDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Product> findAll() throws SQLException {
        String sql = "SELECT * FROM produtos";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return products;
    }

    public Optional<Product> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Product> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public List<Product> findByCategoria(Integer categoriaId) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE fk_categoria_id = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, categoriaId);
        ResultSet rs = stmt.executeQuery();
        
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return products;
    }

    public List<Product> searchByNome(String nome) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE nome LIKE ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, "%" + nome + "%");
        ResultSet rs = stmt.executeQuery();
        
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return products;
    }

    public Product save(Product product) throws SQLException {
        String sql = "BEGIN INSERT INTO produtos (nome, fk_categoria_id, preco, promocao, estoque, imagem, descricao, desconto) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        setProductParams(stmt, product);
        stmt.registerOutParameter(9, Types.NUMERIC);
        stmt.execute();
        
        int id = stmt.getInt(9);
        product.setId(id);
        
        stmt.close();
        factory.closeConnection(conn);
        
        return product;
    }

    private void setProductParams(CallableStatement stmt, Product product) throws SQLException {
        stmt.setString(1, product.getNome());
        stmt.setInt(2, product.getCategoriaId());
        stmt.setFloat(3, product.getPreco());
        stmt.setObject(4, product.getPromocao());
        stmt.setInt(5, product.getEstoque());
        stmt.setString(6, product.getImagem());
        stmt.setString(7, product.getDescricao());
        stmt.setObject(8, product.getDesconto());
    }

    private Product mapResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setNome(rs.getString("nome"));
        product.setCategoriaId(rs.getInt("fk_categoria_id"));
        product.setPreco(rs.getFloat("preco"));
        
        Object promocaoObj = rs.getObject("promocao");
        if (promocaoObj != null) {
            product.setPromocao(((Number) promocaoObj).intValue());
        }
        
        product.setEstoque(rs.getInt("estoque"));
        product.setImagem(rs.getString("imagem"));
        product.setDescricao(rs.getString("descricao"));
        
        Object descontoObj = rs.getObject("desconto");
        if (descontoObj != null) {
            product.setDesconto(((Number) descontoObj).intValue());
        }
        
        return product;
    }
}

