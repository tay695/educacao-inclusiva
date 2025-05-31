package com.ifbaiano.educacaoinclusiva.DAO;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorDAO {
	private Connection conexao;
	private UsuarioDAO usuarioDAO;

	public TutorDAO(Connection conexao) {
		this.conexao = conexao;
		this.usuarioDAO = new UsuarioDAO(conexao);
	}

	public void adicionarTutor(Tutor tutor) {
		try {
			String sqlUsuario = "INSERT INTO Usuario (nome, email, senha, bio, salt, avaliacao) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				stmtUsuario.setString(1, tutor.getRetornaNome());
				stmtUsuario.setString(2, tutor.getEmail());
				stmtUsuario.setString(3, tutor.getSenha());
				stmtUsuario.setString(4, tutor.getBio());
				stmtUsuario.setString(5, tutor.getSalt());
				stmtUsuario.setString(6, tutor.getAvaliacao() != null ? tutor.getAvaliacao() : "");
				stmtUsuario.executeUpdate();

				try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
					if (rs.next()) {
						int idUsuario = rs.getInt(1);
						tutor.setId(idUsuario);

						String sqlTutor = "INSERT INTO Tutor (area_especializacao, id_usuario) VALUES (?, ?)";
						try (PreparedStatement stmtTutor = conexao.prepareStatement(sqlTutor)) {
							stmtTutor.setString(1, tutor.getAreaEspecializacao());
							stmtTutor.setInt(2, idUsuario);
							stmtTutor.executeUpdate();
						}
					}
				}
			}
			System.out.println("Tutor inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir tutor: " + e.getMessage());
		}
	}

	public void atualizarTutor(Tutor tutor) {
		try {
			String sqlUsuario = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, bio = ? WHERE id = ?";
			try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario)) {
				stmt.setString(1, tutor.getRetornaNome());
				stmt.setString(2, tutor.getEmail());
				stmt.setString(3, tutor.getSenha());
				stmt.setString(4, tutor.getBio());
				stmt.setInt(5, tutor.getId());
				stmt.executeUpdate();
			}

			String sqlTutor = "UPDATE Tutor SET area_especializacao = ? WHERE id_usuario = ?";
			try (PreparedStatement stmt = conexao.prepareStatement(sqlTutor)) {
				stmt.setString(1, tutor.getAreaEspecializacao());
				stmt.setInt(2, tutor.getId());
				stmt.executeUpdate();
			}

			System.out.println("Tutor atualizado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar tutor: " + e.getMessage());
		}
	}

	public void excluirTutor(int idUsuario) {
		try {
			String sqlTutor = "DELETE FROM Tutor WHERE id_usuario = ?";
			try (PreparedStatement stmt = conexao.prepareStatement(sqlTutor)) {
				stmt.setInt(1, idUsuario);
				stmt.executeUpdate();
			}

			String sqlUsuario = "DELETE FROM Usuario WHERE id = ?";
			try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario)) {
				stmt.setInt(1, idUsuario);
				stmt.executeUpdate();
			}

			System.out.println("Tutor excluído com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir tutor: " + e.getMessage());
		}
	}

	public Tutor buscarTutorPorEmail(String email) throws SQLException {

		String sql = "SELECT t.area_especializacao, u.id, u.nome, u.email, u.senha, u.bio, u.salt FROM Tutor t JOIN Usuario u ON t.id_usuario = u.id WHERE u.email = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	Tutor tutor = new Tutor( 
                        rs.getString("area_especializacao"),

            			rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("bio"));
            	tutor.setSalt(rs.getString("salt"));
            	return tutor;
            }
		}
		return null;
	}
	public void atualizarCampo(int idUsuario, String campo, String valor) throws SQLException {
		String sql;
		if (campo.equals("area")) {
			sql = "UPDATE Tutor SET area_especializacao = ? WHERE id_usuario = ?";
		} else {
			sql = "UPDATE Usuario SET " + campo + " = ? WHERE id = ?";
		}

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, valor);
			stmt.setInt(2, idUsuario);
			stmt.executeUpdate();
		}
	}

}
