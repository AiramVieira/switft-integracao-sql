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
import com.swift.console.model.Investimento;

public class InvestimentoDAO {

    private FactoryManager factory;
    
    public InvestimentoDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Investimento> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_investimento ORDER BY dt_investimento DESC";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Investimento> investimentos = new ArrayList<>();
        while (rs.next()) {
            investimentos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return investimentos;
    }

    public Optional<Investimento> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_fin_investimento WHERE cd_investimento = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Investimento> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public List<Investimento> findByUsuario(Integer cdUsuario) throws SQLException {
        String sql = "SELECT * FROM t_fin_investimento WHERE cd_usuario = ? ORDER BY dt_investimento DESC";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdUsuario);
        ResultSet rs = stmt.executeQuery();
        
        List<Investimento> investimentos = new ArrayList<>();
        while (rs.next()) {
            investimentos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return investimentos;
    }

    public List<Investimento> findByTipo(Integer cdTipo) throws SQLException {
        String sql = "SELECT * FROM t_fin_investimento WHERE cd_tipo = ? ORDER BY dt_investimento DESC";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdTipo);
        ResultSet rs = stmt.executeQuery();
        
        List<Investimento> investimentos = new ArrayList<>();
        while (rs.next()) {
            investimentos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return investimentos;
    }

    public Investimento save(Investimento investimento) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_investimento (cd_investimento, cd_usuario, cd_tipo, vl_investimento, " +
                     "dt_investimento, rentabilidade_estimada, dt_vencimento) VALUES (?, ?, ?, ?, ?, ?, ?) " +
                     "RETURNING cd_investimento INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        stmt.setInt(2, investimento.getCdUsuario());
        stmt.setInt(3, investimento.getCdTipo());
        stmt.setBigDecimal(4, investimento.getVlInvestimento());
        stmt.setDate(5, investimento.getDtInvestimento() != null ? new java.sql.Date(investimento.getDtInvestimento().getTime()) : null);
        
        if (investimento.getRentabilidadeEstimada() != null) {
            stmt.setBigDecimal(6, investimento.getRentabilidadeEstimada());
        } else {
            stmt.setNull(6, Types.NUMERIC);
        }
        
        if (investimento.getDtVencimento() != null) {
            stmt.setDate(7, new java.sql.Date(investimento.getDtVencimento().getTime()));
        } else {
            stmt.setNull(7, Types.DATE);
        }
        
        stmt.registerOutParameter(8, Types.NUMERIC);
        stmt.execute();
        
        investimento.setCdInvestimento(stmt.getInt(8));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return investimento;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_investimento), 0) + 1 FROM t_fin_investimento";
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

    private Investimento mapResultSet(ResultSet rs) throws SQLException {
        Investimento investimento = new Investimento();
        investimento.setCdInvestimento(rs.getInt("cd_investimento"));
        investimento.setCdUsuario(rs.getInt("cd_usuario"));
        investimento.setCdTipo(rs.getInt("cd_tipo"));
        investimento.setVlInvestimento(rs.getBigDecimal("vl_investimento"));
        investimento.setDtInvestimento(rs.getDate("dt_investimento"));
        investimento.setRentabilidadeEstimada(rs.getBigDecimal("rentabilidade_estimada"));
        investimento.setDtVencimento(rs.getDate("dt_vencimento"));
        return investimento;
    }
}

