package com.swift.console.dao;

import java.sql.*;
import java.util.*;
import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Usuario;

public class UsuarioDAO {

    private FactoryManager factory;
    
    public UsuarioDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Usuario> findAll() throws SQLException {
        String sql = "SELECT * FROM usuario";
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
        String sql = "SELECT * FROM usuario WHERE id = ?";
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

    public Usuario save(Usuario usuario) throws SQLException {
        String sql = "BEGIN INSERT INTO usuario (nome, sobrenome, fk_endereco, telephone, tipo) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        try {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            
            if (usuario.getEnderecoId() != null) {
                stmt.setInt(3, usuario.getEnderecoId());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            
            stmt.setString(4, usuario.getTelephone());
            stmt.setString(5, usuario.getTipo());
            stmt.registerOutParameter(6, Types.NUMERIC);
            
            stmt.execute();
            
            int id = stmt.getInt(6);
            usuario.setId(id);
            
        } catch (SQLException e) {
            System.err.println("Erro SQL ao inserir usuário: " + e.getMessage());
            throw e;
        } finally {
            stmt.close();
            factory.closeConnection(conn);
        }
        
        return usuario;
    }

    private Usuario mapResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        
        Object idObj = rs.getObject("id");
        if (idObj != null) {
            usuario.setId(((Number) idObj).intValue());
        }
        
        usuario.setNome(rs.getString("nome"));
        usuario.setSobrenome(rs.getString("sobrenome"));
        
        Object enderecoIdObj = rs.getObject("fk_endereco");
        if (enderecoIdObj != null) {
            usuario.setEnderecoId(((Number) enderecoIdObj).intValue());
        }
        
        usuario.setTelephone(rs.getString("telephone"));
        usuario.setTipo(rs.getString("tipo"));
        return usuario;
    }
}

