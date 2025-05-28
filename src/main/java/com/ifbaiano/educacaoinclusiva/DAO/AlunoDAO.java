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

		// primeiro vai inseir na tabela do usuário

		int idGerado = usuarioDAO.inserir(aluno);
		if (idGerado != -1) {
			aluno.setId(idGerado); // fonerce o id ao aluno

			// inserindo o aluno na tabela aluno

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

		String sql = "SELECT u.nome, u.email FROM Aluno a JOIN Usuario u ON a.id_usuario = u.id WHERE u.email = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("nome");
				String emailAluno = rs.getString("email");
				return new Aluno(0, nome, emailAluno, null, null);

			}
		}
		return null;
	}

	public Aluno buscarId(int id) throws SQLException {
		String sql = "SELECT nome,id from Aluno  WHERE id = ?";

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
