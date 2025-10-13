package com.swift.console.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.swift.console.factory.FactoryManager;
import com.swift.console.model.BancoUsuario;

public class BancoUsuarioDAO {

    private FactoryManager factory;
    
    public BancoUsuarioDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<BancoUsuario> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_banco_usuario";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<BancoUsuario> bancos = new ArrayList<>();
        while (rs.next()) {
            bancos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return bancos;
    }

    public Optional<BancoUsuario> findByUsuarioId(Integer cdUsuario) throws SQLException {
        String sql = "SELECT * FROM t_fin_banco_usuario WHERE cd_usuario = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, cdUsuario);
        ResultSet rs = stmt.executeQuery();
        
        Optional<BancoUsuario> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public BancoUsuario save(BancoUsuario banco) throws SQLException {
        String sql = "INSERT INTO t_fin_banco_usuario (cd_usuario, nr_agencia, nr_conta, cpf, cd_banco) " +
                     "VALUES (?, ?, ?, ?, ?)";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, banco.getCdUsuario());
        stmt.setInt(2, banco.getNrAgencia());
        stmt.setLong(3, banco.getNrConta());
        stmt.setLong(4, banco.getCpf());
        stmt.setInt(5, banco.getCdBanco());
        
        stmt.executeUpdate();
        stmt.close();
        factory.closeConnection(conn);
        
        return banco;
    }

    private BancoUsuario mapResultSet(ResultSet rs) throws SQLException {
        BancoUsuario banco = new BancoUsuario();
        banco.setCdUsuario(rs.getInt("cd_usuario"));
        banco.setNrAgencia(rs.getInt("nr_agencia"));
        banco.setNrConta(rs.getLong("nr_conta"));
        banco.setCpf(rs.getLong("cpf"));
        banco.setCdBanco(rs.getInt("cd_banco"));
        return banco;
    }
}

