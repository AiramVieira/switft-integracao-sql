package com.swift.console.dao;

import java.math.BigDecimal;
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
import com.swift.console.model.Usuario;

public class UsuarioDAO {

    private FactoryManager factory;
    
    public UsuarioDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM t_fin_usuario";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            usuarios.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return usuarios;
    }

    public Optional<Usuario> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_fin_usuario WHERE cd_usuario = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Usuario> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public List<Usuario> findByAtivo(String ativo) throws SQLException {
        String sql = "SELECT * FROM t_fin_usuario WHERE ativo = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, ativo);
        ResultSet rs = stmt.executeQuery();
        
        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            usuarios.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return usuarios;
    }

    public Usuario save(Usuario usuario) throws SQLException {
        String sql = "BEGIN INSERT INTO t_fin_usuario (cd_usuario, cd_autenticacao, nm_usuario, dt_nascimento, " +
                     "nr_telefone, ativo, vl_saldo) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING cd_usuario INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        // Gerar próximo ID
        Integer nextId = getNextId(conn);
        
        stmt.setInt(1, nextId);
        if (usuario.getCdAutenticacao() != null) {
            stmt.setInt(2, usuario.getCdAutenticacao());
        } else {
            stmt.setNull(2, Types.NUMERIC);
        }
        stmt.setString(3, usuario.getNmUsuario());
        stmt.setDate(4, usuario.getDtNascimento() != null ? new java.sql.Date(usuario.getDtNascimento().getTime()) : null);
        stmt.setString(5, usuario.getNrTelefone());
        stmt.setString(6, usuario.getAtivo());
        stmt.setBigDecimal(7, usuario.getVlSaldo() != null ? usuario.getVlSaldo() : BigDecimal.ZERO);
        stmt.registerOutParameter(8, Types.NUMERIC);
        stmt.execute();
        
        usuario.setCdUsuario(stmt.getInt(8));
        
        stmt.close();
        factory.closeConnection(conn);
        
        return usuario;
    }

    private Integer getNextId(Connection conn) throws SQLException {
        String sql = "SELECT NVL(MAX(cd_usuario), 0) + 1 FROM t_fin_usuario";
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

    private Usuario mapResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setCdUsuario(rs.getInt("cd_usuario"));
        
        int cdAuth = rs.getInt("cd_autenticacao");
        usuario.setCdAutenticacao(rs.wasNull() ? null : cdAuth);
        
        usuario.setNmUsuario(rs.getString("nm_usuario"));
        usuario.setDtNascimento(rs.getDate("dt_nascimento"));
        usuario.setNrTelefone(rs.getString("nr_telefone"));
        usuario.setAtivo(rs.getString("ativo"));
        usuario.setVlSaldo(rs.getBigDecimal("vl_saldo"));
        return usuario;
    }
}
