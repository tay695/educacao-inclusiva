package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class VideoAulaDAO {
	private Connection connection;

	public VideoAulaDAO(Connection connection) {
		this.connection = connection;
	}

	public void inserirVideoaula(VideoAula videoaula) throws SQLException {
		String sql = "INSERT INTO Videoaula (titulo, url, id_modulo) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, videoaula.getTitulo());
			stmt.setString(2, videoaula.getUrl());
			stmt.setInt(3, videoaula.getIdModulo());
			stmt.executeUpdate();

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					videoaula.setId(generatedKeys.getInt(1));
				}
			}
		}
	}

	// Pelo ID do Modulo
	public List<VideoAula> listarVideoaula(int idModulo) throws SQLException {
		List<VideoAula> videoaulas = new ArrayList<>();
		String sql = "SELECT * FROM Videoaula WHERE id_modulo = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

	public void atualizarVideoaula(VideoAula videoaula) throws SQLException {
		String sql = "UPDATE Videoaula SET titulo = ?, url = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, videoaula.getTitulo());
			stmt.setString(2, videoaula.getUrl());
			 stmt.setInt(3, videoaula.getId()); 
			stmt.executeUpdate();
		}
	}

	public void deletarVideoaula(int id) throws SQLException {
		String sql = "DELETE FROM Videoaula WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	public VideoAula buscarVideoaula(int id) throws SQLException {
		String sql = "SELECT * FROM Videoaula WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new VideoAula(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"),
							rs.getInt("id_modulo"));
				}
			}
		}
		return null;
	}
}
