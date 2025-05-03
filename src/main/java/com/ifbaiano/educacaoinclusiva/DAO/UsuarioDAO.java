package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() {
		this.conexao = DBConfig.criarConexao();
	}

	public void inserir(Usuario usuario) throws SQLException {

		    String sql = "INSERT INTO Usuario (nome,email,senha,bio,salt) VALUES (?,?,?,?)";

		    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		        stmt.setString(1, usuario.getRetornaNome());
		        stmt.setString(2, usuario.getEmail());
		        stmt.setString(3, usuario.getSenha());
		        stmt.setString(4, usuario.getBio());
		        stmt.executeUpdate();
		    }
		}


	public Usuario buscarEmail(String email) throws SQLException {	
		String sql = "SELECT * from Usuario  WHERE email = ?";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet resul = stmt.executeQuery();

			if (resul.next()) {
				return new Usuario(resul.getInt("id"), resul.getString("nome"), resul.getString("email"), resul.getString("senha"),
						resul.getString("bio"));
			}
		}
		return null;
	}
}

