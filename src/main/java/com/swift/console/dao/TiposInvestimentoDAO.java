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
import com.swift.console.model.TiposInvestimento;

public class TiposInvestimentoDAO {

    private FactoryManager factory;
    
    public TiposInvestimentoDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<TiposInvestimento> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_tipos_investimento";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<TiposInvestimento> tipos = new ArrayList<>();
        while (rs.next()) {
            tipos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return tipos;
    }

    public Optional<TiposInvestimento> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_fin_tipos_investimento WHERE cd_tipo = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<TiposInvestimento> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public List<TiposInvestimento> findByRisco(String risco) throws SQLException {
        String sql = "SELECT * FROM t_fin_tipos_investimento WHERE risco = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, risco);
        ResultSet rs = stmt.executeQuery();
        
        List<TiposInvestimento> tipos = new ArrayList<>();
        while (rs.next()) {
            tipos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return tipos;
    }

    public TiposInvestimento save(TiposInvestimento tipo) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_tipos_investimento (cd_tipo, risco, nm_tipo) " +
                     "VALUES (?, ?, ?) RETURNING cd_tipo INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        stmt.setString(2, tipo.getRisco());
        stmt.setString(3, tipo.getNmTipo());
        stmt.registerOutParameter(4, Types.NUMERIC);
        stmt.execute();
        
        tipo.setCdTipo(stmt.getInt(4));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return tipo;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_tipo), 0) + 1 FROM t_fin_tipos_investimento";
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

    private TiposInvestimento mapResultSet(ResultSet rs) throws SQLException {
        TiposInvestimento tipo = new TiposInvestimento();
        tipo.setCdTipo(rs.getInt("cd_tipo"));
        tipo.setRisco(rs.getString("risco"));
        tipo.setNmTipo(rs.getString("nm_tipo"));
        return tipo;
    }
}

