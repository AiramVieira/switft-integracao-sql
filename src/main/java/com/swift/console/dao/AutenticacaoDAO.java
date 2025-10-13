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
import com.swift.console.model.Autenticacao;

public class AutenticacaoDAO {

    private FactoryManager factory;
    
    public AutenticacaoDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Autenticacao> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_autenticacao";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Autenticacao> autenticacoes = new ArrayList<>();
        while (rs.next()) {
            autenticacoes.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return autenticacoes;
    }

    public Optional<Autenticacao> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_fin_autenticacao WHERE cd_autenticacao = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Autenticacao> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public Optional<Autenticacao> findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM t_fin_autenticacao WHERE email = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Autenticacao> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public Autenticacao save(Autenticacao autenticacao) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_autenticacao (cd_autenticacao, cd_usuario, email, senha, status_conta) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING cd_autenticacao INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        stmt.setInt(2, autenticacao.getCdUsuario());
        stmt.setString(3, autenticacao.getEmail());
        stmt.setString(4, autenticacao.getSenha());
        stmt.setString(5, autenticacao.getStatusConta());
        stmt.registerOutParameter(6, Types.NUMERIC);
        stmt.execute();
        
        autenticacao.setCdAutenticacao(stmt.getInt(6));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return autenticacao;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_autenticacao), 0) + 1 FROM t_fin_autenticacao";
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

    private Autenticacao mapResultSet(ResultSet rs) throws SQLException {
        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setCdAutenticacao(rs.getInt("cd_autenticacao"));
        autenticacao.setCdUsuario(rs.getInt("cd_usuario"));
        autenticacao.setEmail(rs.getString("email"));
        autenticacao.setSenha(rs.getString("senha"));
        autenticacao.setStatusConta(rs.getString("status_conta"));
        return autenticacao;
    }
}

