package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class VideoAulaDAO {
    private Connection conexao;

    public VideoAulaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public VideoAula inserirVideoaula(VideoAula videoaula) throws SQLException {
        // Validação básica da URL
        if (videoaula.getUrl() == null || !videoaula.getUrl().startsWith("http")) {
            throw new IllegalArgumentException("URL da videoaula inválida");
        }

        String sql = "INSERT INTO Videoaula (titulo, url, id_modulo) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, videoaula.getTitulo());
            stmt.setString(2, videoaula.getUrl());
            stmt.setInt(3, videoaula.getIdModulo());
            
            conexao.setAutoCommit(false);
            try {
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        videoaula.setId(rs.getInt(1));
                        conexao.commit();
                        return videoaula;
                    }
                }
                throw new SQLException("Falha ao inserir videoaula");
            } catch (SQLException e) {
                conexao.rollback();
                throw e;
            } finally {
                conexao.setAutoCommit(true);
            }
        }
    }
   
    public List<VideoAula> listarPorModulo(int idModulo) throws SQLException {
        List<VideoAula> videoaulas = new ArrayList<>();
        String sql = "SELECT id, titulo, url, id_modulo FROM Videoaula WHERE id_modulo = ?";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idModulo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VideoAula videoaula = new VideoAula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("url"),
                        rs.getInt("id_modulo")
                    );
                    videoaulas.add(videoaula);
                }
            }
        }
        return videoaulas;
    }
    
 // No VideoAulaDAO.java
    public List<VideoAula> listarPorTutor(int tutorId) throws SQLException {
        List<VideoAula> videoAulas = new ArrayList<>();
        String sql = """
            SELECT v.id, v.titulo, v.url, v.id_modulo
            FROM Videoaula v
            JOIN Modulo m ON v.id_modulo = m.id
            JOIN Curso c ON m.id_curso = c.id
            WHERE c.id_tutor = ?
            ORDER BY v.titulo
        """;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, tutorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VideoAula video = new VideoAula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("url"),
                        rs.getInt("id_modulo")
                    );
                    videoAulas.add(video);
                }
            }
        }
        return videoAulas;
    }
    public void atualizarVideoaula(VideoAula videoaula) throws SQLException {
        String sql = "UPDATE Videoaula SET titulo = ?, url = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, videoaula.getTitulo());
            stmt.setString(2, videoaula.getUrl());
            stmt.setInt(3, videoaula.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarVideoaula(int id) throws SQLException {
        String sql = "DELETE FROM Videoaula WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public VideoAula buscarVideoaula(int id) throws SQLException {
        String sql = "SELECT * FROM Videoaula WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new VideoAula(
                        rs.getInt("id"), 
                        rs.getString("titulo"), 
                        rs.getString("url"),
                        rs.getInt("id_modulo")
                    );
                }
            }
        }
        return null;
    }

	
}