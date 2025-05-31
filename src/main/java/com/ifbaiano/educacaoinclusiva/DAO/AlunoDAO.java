package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.model.Aluno;

public class AlunoDAO {
	private Connection conexao;
	private UsuarioDAO usuarioDAO;

	public AlunoDAO(Connection connection) {
		this.conexao = connection;
		this.usuarioDAO = new UsuarioDAO(connection);
	}

	public void inserirAluno(Aluno aluno) throws SQLException {
		int idGerado = usuarioDAO.inserir(aluno);
		if (idGerado != -1) {
			aluno.setId(idGerado);
			String sql = "INSERT INTO Aluno (id_usuario) VALUES (?)";
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, idGerado);
				stmt.executeUpdate();
			}

		} else {
			throw new SQLException("Houve um erro ao inserir o novo usuário aluno");
		}
	}

	public Aluno buscarAlunoPorEmail(String email) throws SQLException {
	    String sql = "SELECT u.id, u.nome, u.email, u.senha, u.bio, u.salt FROM Aluno a JOIN Usuario u ON a.id_usuario = u.id WHERE u.email = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	Aluno aluno = new Aluno( 
            			rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("bio"));
            	aluno.setSalt(rs.getString("salt"));
            	return aluno;
            }
		}
		return null;
	}

	public Aluno buscarId(int id) throws SQLException {
		String sql = "SELECT u.id, u.nome FROM Usuario u JOIN Aluno a ON u.id = a.id_usuario WHERE u.id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Aluno(rs.getInt("id"), rs.getString("nome"), null, null, null);
			}
		}
		return null;
	}

}
