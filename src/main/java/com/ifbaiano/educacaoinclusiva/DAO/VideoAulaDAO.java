package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class VideoAulaDAO {
	private Connection conexao;

	public VideoAulaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public VideoAula inserirVideoaula(VideoAula videoaula) throws SQLException {
	    String sql = "INSERT INTO VideoAula (titulo, url, id_modulo) VALUES (?, ?, ?)";
	    
	    try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, videoaula.getTitulo());
	        stmt.setString(2, videoaula.getUrl());
	        stmt.setInt(3, videoaula.getModuloId());  
	        
	      
	        int affectedRows = stmt.executeUpdate();
	        
	        if (affectedRows == 0) {
	            throw new SQLException("Falha ao inserir vídeo aula, nenhuma linha afetada.");
	        }
	        
	     
	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                videoaula.setId(rs.getInt(1));
	            } else {
	                throw new SQLException("Falha ao obter o ID do vídeo aula, nenhum ID obtido.");
	            }
	        }
	        
	        return videoaula;
	    }
	}
	public List<VideoAula> listarPorAulas() throws SQLException {
		List<VideoAula> videoaulas = new ArrayList<>();
		String sql = "SELECT * FROM VideoAula";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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

	public List<VideoAula> listarPorTutor(int tutorId) throws SQLException {
		List<VideoAula> videoAulas = new ArrayList<>();
		String sql = """
				    SELECT v.id, v.titulo, v.url, v.id_modulo
				    FROM VideoAula v
				    JOIN Modulo m ON v.id_modulo = m.id
				    JOIN Curso c ON m.id_curso = c.id
				    WHERE c.id_tutor = ?
				    ORDER BY v.titulo
				""";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, tutorId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					VideoAula video = new VideoAula(rs.getInt("id"), rs.getString("titulo"), rs.getString("url"),
							rs.getInt("id_modulo"));
					videoAulas.add(video);
				}
			}
		}
		return videoAulas;
	}

	public void atualizarVideoaula(VideoAula videoaula) throws SQLException {
		String sql = "UPDATE VideoAula SET titulo = ?, url = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, videoaula.getTitulo());
			stmt.setString(2, videoaula.getUrl());
			stmt.setInt(3, videoaula.getId());
			stmt.executeUpdate();
		}
	}

	public void deletarVideoaula(int id) throws SQLException {
		String sql = "DELETE FROM VideoAula WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	public VideoAula buscarVideoaula(int id) throws SQLException {
		String sql = "SELECT * FROM VideoAula WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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

	public List<VideoAula> buscarPorModulo(int idModulo) throws SQLException {
		List<VideoAula> videos = new ArrayList<>();

		String sql = "SELECT * FROM VideoAula WHERE id_modulo = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, idModulo);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					VideoAula video = new VideoAula();
					video.setId(rs.getInt("id"));
					video.setTitulo(rs.getString("titulo"));
					video.setUrl(rs.getString("url"));
					video.setmoduloId(rs.getInt("id_modulo"));
					videos.add(video);
				}
			}
		}

		return videos;
	}
}
