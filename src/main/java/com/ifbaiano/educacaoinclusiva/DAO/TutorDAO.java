package com.ifbaiano.educacaoinclusiva.DAO;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import java.sql.*;

public class TutorDAO {

	private Connection conexao;

	public TutorDAO() {
		this.conexao = DBConfig.criarConexao();
	}

	public void adicionarTutor(Tutor tutor) {
		String sql = "INSERT INTO tutor (nome, email, areaEspecializacao) VALUES (?, ?, ?)";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tutor.getRetornaNome());
			stmt.setString(2, tutor.getEmail());
			stmt.setString(3, tutor.getAreaEspecializacao());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				tutor.setId(idGerado);
			}

			stmt.close();
		} catch (SQLException e) {
			System.out.println("Houve um erro ao inserir" + e);
		}
	}

	// Atualizar tutor
	public void atualizarTutor(Tutor tutor) {
		String sql = "UPDATE tutor SET nome = ?, email = ? WHERE id = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, tutor.getRetornaNome());
			stmt.setString(2, tutor.getEmail());
			stmt.setInt(3, tutor.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// deletar
	public void excluirTutor(int id) {
		String sql = "DELETE FROM tutor WHERE id = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}