package com.swift.console.dao;

import java.sql.*;
import java.util.*;
import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Categoria;

public class CategoriaDAO {

    private FactoryManager factory;
    
    public CategoriaDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Categoria> findAll() throws SQLException {
        String sql = "SELECT * FROM categoria";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Categoria> categorias = new ArrayList<>();
        while (rs.next()) {
            categorias.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return categorias;
    }

    public Optional<Categoria> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Categoria> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public Categoria save(Categoria categoria) throws SQLException {
        String sql = "BEGIN INSERT INTO categoria (descricao) VALUES (?) RETURNING id INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        stmt.setString(1, categoria.getDescricao());
        stmt.registerOutParameter(2, Types.NUMERIC);
        stmt.execute();
        
        int id = stmt.getInt(2);
        categoria.setId(id);
        
        stmt.close();
        factory.closeConnection(conn);
        
        return categoria;
    }

    private Categoria mapResultSet(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setDescricao(rs.getString("descricao"));
        return categoria;
    }
}

