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

				try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("dbConfig.properties\"")) {
			if (input == null) {
				throw new RuntimeException("Arquivo config.properties não encontrado no classpath");
			}

			properties.load(input);

		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
		}

				String user = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		String url = properties.getProperty("db.url");

		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar conexão com o banco de dados", e);
		}
	}

	private DBConfig() {
	}
}
