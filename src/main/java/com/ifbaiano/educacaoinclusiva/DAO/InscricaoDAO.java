package com.ifbaiano.educacaoinclusiva.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.model.Inscricao;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class InscricaoDAO {
	 private Connection conexao;

	    public InscricaoDAO(Connection conexao){
	        this.conexao = conexao;
	    }

	    public void inscreverEmVideoAula(Inscricao inscricao) throws SQLException {
	        String sql = "INSERT INTO Inscricao (id_usuario, id_videoaula) VALUES (?, ?)";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setInt(1, inscricao.getIdUsuario());
	            stmt.setInt(2, inscricao.getIdVideoAula());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public boolean jaInscrito(int idUsuario, int idVideoAula) throws SQLException {
	        String sql = "SELECT COUNT(*) FROM Inscricao WHERE id_usuario = ? AND id_videoaula = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setInt(1, idUsuario);
	            stmt.setInt(2, idVideoAula);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        }
	        return false;
	    }
	    public List<VideoAula> listarInscricoesPorUsuario(int idUsuario) throws SQLException {
	    List<VideoAula> inscricoes = new ArrayList<>();
	    String sql = """
	        SELECT v.id, v.titulo, v.url, v.id_modulo 
	        FROM Inscricao i 
	        JOIN VideoAula v ON i.id_videoaula = v.id 
	        WHERE i.id_usuario = ?
	    """;

	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, idUsuario);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                VideoAula v = new VideoAula();
	                v.setId(rs.getInt("id"));
	                v.setTitulo(rs.getString("titulo"));
	                v.setUrl(rs.getString("url"));
	                v.setmoduloId(rs.getInt("id_modulo"));
	                inscricoes.add(v);
	            }
	        }
	    }

	    return inscricoes;
	}

	
}
