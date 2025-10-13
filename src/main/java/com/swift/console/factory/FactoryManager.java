package com.swift.console.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryManager {

    private static final Logger LOG = LoggerFactory.getLogger(FactoryManager.class);
    
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USERNAME = "rm564323";
    private static final String PASSWORD = "111199";
    
    private static FactoryManager instance;
    
    private FactoryManager() { }
    
    public static FactoryManager getInstance() {
        if (instance == null) {
            instance = new FactoryManager();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        LOG.info("CONECTANDO AO BANCO DE DADOS");
        Properties props = new Properties();
        props.setProperty("user", USERNAME);
        props.setProperty("password", PASSWORD);
        System.setProperty("oracle.net.CONNECT_TIMEOUT", "10000");
        System.setProperty("oracle.net.READ_TIMEOUT", "15000");
        
        try {
            Connection conn = DriverManager.getConnection(URL, props);
            LOG.info("Conexão estabelecida com sucesso!");
            return conn;
        } catch (SQLException e) {
            LOG.error("Erro ao conectar ao banco de dados: {}", e.getMessage());
            throw e;
        }
    }
    
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                LOG.info("Conexão fechada com sucesso");
            } catch (SQLException e) {
                LOG.error("Erro ao fechar conexão: {}", e.getMessage());
            }
        }
    }
}

