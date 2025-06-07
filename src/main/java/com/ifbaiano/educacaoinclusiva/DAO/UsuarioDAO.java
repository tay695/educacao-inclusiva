package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO(Connection connection) {
		this.conexao = connection;
	}
	
	public Connection getConexao() {
		return this.conexao;
	}
	public int inserir(Usuario usuario) throws SQLException {
	    String sql = "INSERT INTO Usuario (nome, email, senha, bio, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, usuario.getRetornaNome());
	        stmt.setString(2, usuario.getEmail());
	        stmt.setString(3, usuario.getSenha());
	        stmt.setString(4, usuario.getBio());
	        stmt.setString(5, usuario.getTipoUsuario());

	        int linhasAfetadas = stmt.executeUpdate();
	        if (linhasAfetadas == 0) {
	            throw new SQLException("Falha ao inserir usuário.");
	        }

	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("Falha ao obter ID do usuário.");
	            }
	        }
	    }
	}

	//*public int inserir(Tutor usuario) throws SQLException {
	//*	String sql = "INSERT INTO Usuario (nome, email, senha,bio,tipo_usuario ) VALUES (?, ?, ?, ?, ?)";
     //*   String sqlTutor = "INSERT INTO Tutor (area_especializacao,id_usuario) VALUES (?,?)";

		//*try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	       //* stmt.setString(1, usuario.getAreaEspecializacao());
	    //*	stmt.setString(2, usuario.getRetornaNome());
	      //*  stmt.setString(3, usuario.getEmail());
	      //*  stmt.setString(4, usuario.getSenha());
	        //*stmt.setString(5, usuario.getBio());
	        //*stmt.setString(6,usuario.getTipoUsuario());

	        //*int affectedRows = stmt.executeUpdate();

	        //*if (affectedRows == 0) {
	           //* throw new SQLException("Falha ao inserir usuário, nenhuma linha afetada.");
	        //*}

	       //* try (ResultSet rs = stmt.getGeneratedKeys()) {
	          //*  if (rs.next()) {
	             //*   return rs.getInt(1);
	            //*} else {
	               //* throw new SQLException("Falha ao obter ID gerado.");
	            
	        


	public int inserirAluno(Aluno usuario) throws SQLException {
		String sql = "INSERT INTO Usuario (nome, email, senha,bio,tipo_usuario ) VALUES (?, ?, ?, ?,?)";
        String sqlAluno = "INSERT INTO Aluno (id_usuario) VALUES (?)";
		try ( PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			conexao.setAutoCommit(false);
	        stmt.setString(1, usuario.getRetornaNome());
	        stmt.setString(2, usuario.getEmail());
	        stmt.setString(3, usuario.getSenha());
	        stmt.setString(5, usuario.getBio());
	        stmt.setString(6,usuario.getTipoUsuario());
	        System.out.println(usuario);
	        int affectedRows = stmt.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Falha ao inserir usuário, nenhuma linha afetada.");
	        }

	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            } else {
	                throw new SQLException("Falha ao obter ID gerado.");
	            }
	        }
	    }
	}

	public Usuario buscarEmail(String email) throws SQLException {
		String sql = "SELECT * from Usuario  WHERE email = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet resul = stmt.executeQuery();
			if (resul.next()) {
				Usuario usuario = new Usuario(resul.getInt("id"), resul.getString("nome"), resul.getString("email"),resul.getString("senha"), resul.getString("bio"), resul.getString("tipo_usuario"));
				return usuario;
			}
		}
		return null;
	}

	public Usuario buscarId(int id) throws SQLException {
		String sql = "SELECT * FROM Usuario WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet resul = stmt.executeQuery();
			if (resul.next()) {
				Usuario usuario = new Usuario(resul.getInt("id"), resul.getString("nome"), resul.getString("email"),resul.getString("senha"), resul.getString("bio"),  resul.getString("tipo_usuario"));
				return usuario;
			}
		}
		return null;
	}

	public boolean emailJaExiste(String email) throws SQLException {
		String sql = "SELECT 1 FROM Usuario WHERE email = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		}
	}

	

	public void atualizarUsuario(Usuario usuario) throws SQLException {
		String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, bio = ?, salt = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, usuario.getRetornaNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getBio());
			stmt.setInt(6, usuario.getId());
			stmt.executeUpdate();
		}
	}

	public void excluirUsuario(int id) throws SQLException {
		String sql = "DELETE FROM Usuario WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
}
