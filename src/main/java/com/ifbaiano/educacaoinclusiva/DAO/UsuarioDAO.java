package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO(Connection connection) {
		this.conexao =  connection;
	}

	public int inserir(Usuario usuario) throws SQLException {
	    String sql = "INSERT INTO Usuario (nome, email, senha, bio, salt) VALUES (?, ?, ?, ?, ?)";

	    
	    String salt = SenhaUtils.gerarSalt();

	    String hashSenha = SenhaUtils.gerarHashSenha(usuario.getSenha(), salt);

	    try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, usuario.getRetornaNome());
	        stmt.setString(2, usuario.getEmail());
	        stmt.setString(3, hashSenha); 
	        stmt.setString(4, usuario.getBio());
	        stmt.setString(5, salt);      

	        stmt.executeUpdate();

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                int idGerado = rs.getInt(1);
	                usuario.setId(idGerado);
	                return idGerado;
	            }
	        }
	    }
	    return -1;
	}


	public Usuario buscarEmail(String email) throws SQLException {
		String sql = "SELECT * from Usuario  WHERE email = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) { 
			stmt.setString(1, email);
			ResultSet resul = stmt.executeQuery();
			if (resul.next()) {
	            Usuario usuario = new Usuario(
	                    resul.getInt("id"),
	                    resul.getString("nome"),
	                    resul.getString("email"),
	                    resul.getString("senha"),
	                    resul.getString("bio")
	                );
	                usuario.setSalt(resul.getString("salt"));
	                return usuario;
	            }
	        }
	        return null;
	    }


	public Usuario buscarId(int id) throws SQLException {
		String sql = "SELECT nome,id from Usuario  WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet resul = stmt.executeQuery();

			if (resul.next()) {
				return new Usuario(resul.getInt("id"), resul.getString("nome"), null, null, null);
			}
		}
		return null;
	}
	

}
