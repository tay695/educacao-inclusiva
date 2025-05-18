package com.ifbaiano.educacaoinclusiva.DAO;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorDAO {
	private Connection conexao;

	public TutorDAO() {
		this.conexao = DBConfig.criarConexao();
	}

	public void adicionarTutor(Tutor tutor) {
		try {
			//Inserir na tabela Usuario
			String sqlUsuario = "INSERT INTO Usuario (nome, email, senha, bio, salt, avaliacao) VALUES (?, ?, ?, ?, ?, ?)";
			try (PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				stmtUsuario.setString(1, tutor.getRetornaNome());
				stmtUsuario.setString(2, tutor.getEmail());
				stmtUsuario.setString(3, tutor.getSenha());
				stmtUsuario.setString(4, tutor.getBio());
				stmtUsuario.setString(5, "salt"); // 
				stmtUsuario.setString(6, ""); // 
				stmtUsuario.executeUpdate();

				try (ResultSet rs = stmtUsuario.getGeneratedKeys()) {
					if (rs.next()) {
						int idUsuario = rs.getInt(1);
						tutor.setId(idUsuario);

						//  Inserir na tabela Tutor com o id do usuário
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
			// Atualiza a tabela Usuario
			String sqlUsuario = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, bio = ? WHERE id = ?";
			try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario)) {
				stmt.setString(1, tutor.getRetornaNome());
				stmt.setString(2, tutor.getEmail());
				stmt.setString(3, tutor.getSenha());
				stmt.setString(4, tutor.getBio());
				stmt.setInt(5, tutor.getId());
				stmt.executeUpdate();
			}

			// Atualiza a tabela Tutor
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
			//  exclui da tabela Tutor
			String sqlTutor = "DELETE FROM Tutor WHERE id_usuario = ?";
			try (PreparedStatement stmt = conexao.prepareStatement(sqlTutor)) {
				stmt.setInt(1, idUsuario);
				stmt.executeUpdate();
			}

			//  exclui da tabela Usuario
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
}
