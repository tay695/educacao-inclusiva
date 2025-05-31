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
	public VideoAulaDAO() {
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
			public List<VideoAula> listarPorTutor(int idTutor) throws SQLException {
	    List<VideoAula> lista = new ArrayList<>();

	    String sql = """
	        SELECT v.id, v.titulo, v.url, v.id_modulo
	        FROM Videoaula v
	        JOIN Modulo m ON v.id_modulo = m.id
	        JOIN Curso c ON m.id_curso = c.id
	        WHERE c.id_tutor = ?
	    """;

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {

	        stmt.setInt(1, idTutor);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            VideoAula v = new VideoAula(
	                rs.getInt("id"),
	                rs.getString("titulo"),
	                rs.getString("url"),
	                rs.getInt("id_modulo")
	            );
	            lista.add(v);
	        }
	    }

	    return lista;
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
