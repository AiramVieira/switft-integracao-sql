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
import com.swift.console.model.Recebimentos;

public class RecebimentosDAO {

    private FactoryManager factory;
    
    public RecebimentosDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Recebimentos> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_recebimentos ORDER BY dt_recebimento DESC";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Recebimentos> recebimentos = new ArrayList<>();
        while (rs.next()) {
            recebimentos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return recebimentos;
    }

    public Optional<Recebimentos> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_fin_recebimentos WHERE cd_recebimento = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Recebimentos> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public List<Recebimentos> findByUsuario(Integer cdUsuario) throws SQLException {
        String sql = "SELECT * FROM t_fin_recebimentos WHERE cd_usuario = ? ORDER BY dt_recebimento DESC";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdUsuario);
        ResultSet rs = stmt.executeQuery();
        
        List<Recebimentos> recebimentos = new ArrayList<>();
        while (rs.next()) {
            recebimentos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return recebimentos;
    }

    public List<Recebimentos> findByCategoria(Integer cdCategoria) throws SQLException {
        String sql = "SELECT * FROM t_fin_recebimentos WHERE cd_categoria = ? ORDER BY dt_recebimento DESC";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdCategoria);
        ResultSet rs = stmt.executeQuery();
        
        List<Recebimentos> recebimentos = new ArrayList<>();
        while (rs.next()) {
            recebimentos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return recebimentos;
    }

    public Recebimentos save(Recebimentos recebimento) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_recebimentos (cd_recebimento, cd_usuario, cd_categoria, nm_recebimento, " +
                     "dt_recebimento, vl_recebimento, ds_recebimento) VALUES (?, ?, ?, ?, ?, ?, ?) " +
                     "RETURNING cd_recebimento INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        stmt.setInt(2, recebimento.getCdUsuario());
        stmt.setInt(3, recebimento.getCdCategoria());
        stmt.setString(4, recebimento.getNmRecebimento());
        stmt.setDate(5, recebimento.getDtRecebimento() != null ? new java.sql.Date(recebimento.getDtRecebimento().getTime()) : null);
        stmt.setBigDecimal(6, recebimento.getVlRecebimento());
        stmt.setString(7, recebimento.getDsRecebimento());
        stmt.registerOutParameter(8, Types.NUMERIC);
        stmt.execute();
        
        recebimento.setCdRecebimento(stmt.getInt(8));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return recebimento;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_recebimento), 0) + 1 FROM t_fin_recebimentos";
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

    private Recebimentos mapResultSet(ResultSet rs) throws SQLException {
        Recebimentos recebimento = new Recebimentos();
        recebimento.setCdRecebimento(rs.getInt("cd_recebimento"));
        recebimento.setCdUsuario(rs.getInt("cd_usuario"));
        recebimento.setCdCategoria(rs.getInt("cd_categoria"));
        recebimento.setNmRecebimento(rs.getString("nm_recebimento"));
        recebimento.setDtRecebimento(rs.getDate("dt_recebimento"));
        recebimento.setVlRecebimento(rs.getBigDecimal("vl_recebimento"));
        recebimento.setDsRecebimento(rs.getString("ds_recebimento"));
        return recebimento;
    }
}

