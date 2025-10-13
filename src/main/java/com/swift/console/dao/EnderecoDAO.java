package com.swift.console.dao;

import java.sql.*;
import java.util.*;
import com.swift.console.factory.FactoryManager;
import com.swift.console.model.Endereco;

public class EnderecoDAO {

    private FactoryManager factory;
    
    public EnderecoDAO() {
        this.factory = FactoryManager.getInstance();
    }

    public List<Endereco> findAll() throws SQLException {
        String sql = "SELECT * FROM endereco";
        Connection conn = factory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Endereco> enderecos = new ArrayList<>();
        while (rs.next()) {
            enderecos.add(mapResultSet(rs));
        }
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return enderecos;
    }

    public Optional<Endereco> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        Connection conn = factory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Optional<Endereco> result = rs.next() ? Optional.of(mapResultSet(rs)) : Optional.empty();
        
        rs.close();
        stmt.close();
        factory.closeConnection(conn);
        
        return result;
    }

    public Endereco save(Endereco endereco) throws SQLException {
        String sql = "BEGIN INSERT INTO endereco (descricao, cep, latitude, longitude) " +
                     "VALUES (?, ?, ?, ?) RETURNING id INTO ?; END;";
        Connection conn = factory.getConnection();
        CallableStatement stmt = conn.prepareCall(sql);
        
        stmt.setString(1, endereco.getDescricao());
        stmt.setString(2, endereco.getCep());
        stmt.setBigDecimal(3, endereco.getLatitude());
        stmt.setBigDecimal(4, endereco.getLongitude());
        stmt.registerOutParameter(5, Types.NUMERIC);
        stmt.execute();
        
        int id = stmt.getInt(5);
        endereco.setId(id);
        
        stmt.close();
        factory.closeConnection(conn);
        
        return endereco;
    }

    private Endereco mapResultSet(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("id"));
        endereco.setDescricao(rs.getString("descricao"));
        endereco.setCep(rs.getString("cep"));
        endereco.setLatitude(rs.getBigDecimal("latitude"));
        endereco.setLongitude(rs.getBigDecimal("longitude"));
        return endereco;
    }
}

