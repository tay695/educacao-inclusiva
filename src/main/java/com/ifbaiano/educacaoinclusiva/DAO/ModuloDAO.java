package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class ModuloDAO {
	private Connection conexao;

	public ModuloDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserirModulo(Modulo modulo) throws SQLException {
		String sql = "INSERT INTO Modulo (titulo, descricao) VALUES ( ?, ?)";
		try (Connection conn = DBConfig.criarConexao();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, modulo.getTitulo());
			stmt.setString(2, modulo.getDescricao());

			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					modulo.setId(rs.getInt(1));
				}
			}
		}
	}

	public List<Modulo> listarPorTutor(int tutorId) throws SQLException {
		List<Modulo> modulos = new ArrayList<>();
		String sql = """
				 SELECT m.id, m.titulo, m.descricao
				FROM Modulo m
				WHERE c.id_tutor = ?

				            """;

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, tutorId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Modulo modulo = new Modulo(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"));
					modulos.add(modulo);
				}
			}
		}
		return modulos;
	}

	

	public void atualizarModulo(Modulo modulo) throws SQLException {
		String sql = "UPDATE Modulo SET titulo = ?, descricao = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, modulo.getTitulo());
			stmt.setString(2, modulo.getDescricao());
			stmt.setInt(3, modulo.getId());

			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas == 0) {
				throw new SQLException("Nenhum módulo encontrado com o ID: " + modulo.getId());
			}
		}
	}

	public Modulo buscarModuloCompleto(int idModulo) throws SQLException {
		String sql = "SELECT id, titulo, descricao, id_curso FROM Modulo WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, idModulo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					List<VideoAula> videoaulas = listarVideoaulasModulo(idModulo);
					return new Modulo(rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"));
				}
			}
		}
		return null;
	}

	private List<VideoAula> listarVideoaulasModulo(int idModulo) throws SQLException {
		List<VideoAula> videoaulas = new ArrayList<>();
		String sql = "SELECT * FROM Videoaula WHERE id_modulo = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, idModulo);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					VideoAula videoaula = new VideoAula(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"),
							rs.getInt("id_modulo"));
					videoaulas.add(videoaula);
				}
			}
		}
		return videoaulas;
	}

	public void deletarModulo(int idModulo) throws SQLException {
		String sqlDeleteVideos = "DELETE FROM Videoaula WHERE id_modulo = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlDeleteVideos)) {
			stmt.setInt(1, idModulo);
			stmt.executeUpdate();
		}
		String sqlDeleteModulo = "DELETE FROM Modulo WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlDeleteModulo)) {
			stmt.setInt(1, idModulo);
			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas == 0) {
				throw new SQLException("Módulo não encontrado ou já deletado.");
			}
		}
	}

	public List<Modulo> listarModulos() {
		List<Modulo> modulos = new ArrayList<>();
		String sql = "SELECT id, titulo, descricao, id_curso FROM Modulo";

		try (Connection conn = DBConfig.criarConexao();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Modulo modulo = new Modulo();
				modulo.setId(rs.getInt(1));
				modulo.setTitulo(rs.getString("titulo"));
				modulo.setDescricao(rs.getString("descricao"));
				modulos.add(modulo);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar módulos: " + e.getMessage());
			e.printStackTrace();
		}

		return modulos;
	}
}
