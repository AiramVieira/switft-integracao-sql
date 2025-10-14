package com.swift.console.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Categoria;

public class CategoriaDAO {

    private FactoryManager factory;
    
    public CategoriaDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Categoria> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_categoria";
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
        String sql = "SELECT * FROM t_fin_categoria WHERE cd_categoria = ?";
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

    public List<Categoria> findByTipo(String tipo) throws SQLException {
        String sql = "SELECT * FROM t_fin_categoria WHERE tp_categoria = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, tipo);
        ResultSet rs = stmt.executeQuery();
        
        List<Categoria> categorias = new ArrayList<>();
        while (rs.next()) {
            categorias.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return categorias;
    }

    public Categoria save(Categoria categoria) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_categoria (cd_categoria, nm_categoria, tp_categoria) " +
                     "VALUES (?, ?, ?) RETURNING cd_categoria INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        stmt.setString(2, categoria.getNmCategoria());
        stmt.setString(3, categoria.getTpCategoria());
        stmt.registerOutParameter(4, Types.NUMERIC);
        stmt.execute();
        
        categoria.setCdCategoria(stmt.getInt(4));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return categoria;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_categoria), 0) + 1 FROM t_fin_categoria";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        int nextId = 1;
        if (rs.next()) {
            nextId = rs.getInt(1);
        }
        
        rs.close();
        stmt.close();
        return nextId;
    }

    private Categoria mapResultSet(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setCdCategoria(rs.getInt("cd_categoria"));
        categoria.setNmCategoria(rs.getString("nm_categoria"));
        categoria.setTpCategoria(rs.getString("tp_categoria"));
        return categoria;
    }
}
