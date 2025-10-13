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
import com.swift.console.model.Gastos;

public class GastosDAO {

    private FactoryManager factory;
    
    public GastosDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Gastos> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_gastos ORDER BY dt_gasto DESC";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Gastos> gastos = new ArrayList<>();
        while (rs.next()) {
            gastos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return gastos;
    }

    public Optional<Gastos> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_fin_gastos WHERE cd_gasto = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Gastos> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public List<Gastos> findByUsuario(Integer cdUsuario) throws SQLException {
        String sql = "SELECT * FROM t_fin_gastos WHERE cd_usuario = ? ORDER BY dt_gasto DESC";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdUsuario);
        ResultSet rs = stmt.executeQuery();
        
        List<Gastos> gastos = new ArrayList<>();
        while (rs.next()) {
            gastos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return gastos;
    }

    public List<Gastos> findByCategoria(Integer cdCategoria) throws SQLException {
        String sql = "SELECT * FROM t_fin_gastos WHERE cd_categoria = ? ORDER BY dt_gasto DESC";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdCategoria);
        ResultSet rs = stmt.executeQuery();
        
        List<Gastos> gastos = new ArrayList<>();
        while (rs.next()) {
            gastos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return gastos;
    }

    public Gastos save(Gastos gasto) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_gastos (cd_gasto, cd_usuario, cd_categoria, nm_gasto, dt_gasto, " +
                     "vl_gasto, ds_gasto) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING cd_gasto INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        stmt.setInt(2, gasto.getCdUsuario());
        stmt.setInt(3, gasto.getCdCategoria());
        stmt.setString(4, gasto.getNmGasto());
        stmt.setDate(5, gasto.getDtGasto() != null ? new java.sql.Date(gasto.getDtGasto().getTime()) : null);
        stmt.setBigDecimal(6, gasto.getVlGasto());
        stmt.setString(7, gasto.getDsGasto());
        stmt.registerOutParameter(8, Types.NUMERIC);
        stmt.execute();
        
        gasto.setCdGasto(stmt.getInt(8));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return gasto;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_gasto), 0) + 1 FROM t_fin_gastos";
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

    private Gastos mapResultSet(ResultSet rs) throws SQLException {
        Gastos gasto = new Gastos();
        gasto.setCdGasto(rs.getInt("cd_gasto"));
        gasto.setCdUsuario(rs.getInt("cd_usuario"));
        gasto.setCdCategoria(rs.getInt("cd_categoria"));
        gasto.setNmGasto(rs.getString("nm_gasto"));
        gasto.setDtGasto(rs.getDate("dt_gasto"));
        gasto.setVlGasto(rs.getBigDecimal("vl_gasto"));
        gasto.setDsGasto(rs.getString("ds_gasto"));
        return gasto;
    }
}

