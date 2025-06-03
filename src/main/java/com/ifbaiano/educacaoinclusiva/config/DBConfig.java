package com.ifbaiano.educacaoinclusiva.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {

	public static Connection criarConexao() {
	    Properties properties = new Properties();

	    // Tenta carregar config.properties do classpath
	    System.out.println("Tentando carregar config.properties...");
	    InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("config.properties");

	    if (input == null) {
	        System.out.println("config.properties NÃO ENCONTRADO!");
	        throw new RuntimeException("Arquivo config.properties não encontrado no classpath");
	    } else {
	        System.out.println("config.properties encontrado com sucesso!");
	    }

	    try {
	        properties.load(input);
	    } catch (IOException e) {
	        throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
	    }

	    String user = properties.getProperty("db.username");
	    String password = properties.getProperty("db.password");
	    String url = properties.getProperty("db.url");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        return DriverManager.getConnection(url, user, password);
	    } catch (SQLException | ClassNotFoundException e) {
	        throw new RuntimeException("Erro ao criar conexão com o banco de dados", e);
	    }
	}

	private DBConfig() {
	}

	public static Connection getConnection() {
		return criarConexao();
	}
}
