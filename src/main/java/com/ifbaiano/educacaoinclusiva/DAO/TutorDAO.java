package com.ifbaiano.educacaoinclusiva.DAO;

import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorDAO {
	private Connection conexao;

	public TutorDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserirTutor(Tutor tutor) throws SQLException {
	    String sql = "INSERT INTO Tutor (area_especializacao, id_usuario) VALUES (?, ?)";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, tutor.getAreaEspecializacao());
	        stmt.setInt(2, tutor.getIdUsuario()); 
	        stmt.executeUpdate();
	    }
	}


	public Tutor buscarPorIdUsuario(int usuarioId) throws SQLException {
	    String sql = "SELECT area_especializacao FROM Tutor WHERE id_usuario = ?";
	    Tutor tutor = null;
	    
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, usuarioId);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            tutor = new Tutor();
	            tutor.setIdUsuario(usuarioId);
	            tutor.setAreaEspecializacao(rs.getString("area_especializacao"));
	        }
	    }
	    return tutor;
	}
	  public void atualizarTutor(Tutor tutor) throws SQLException {
	        String sqlUsuario = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, bio = ? WHERE id = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario)) {
	            stmt.setString(1, tutor.getRetornaNome());
	            stmt.setString(2, tutor.getEmail());
	            stmt.setString(3, tutor.getSenha());
	            stmt.setString(4, tutor.getBio());
	            stmt.setInt(6, tutor.getId());
	            stmt.executeUpdate();
	        }

	        String sqlTutor = "UPDATE Tutor SET area_especializacao = ? WHERE id_usuario = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sqlTutor)) {
	            stmt.setString(1, tutor.getAreaEspecializacao());
	            stmt.setInt(2, tutor.getId());
	            stmt.executeUpdate();
	        }
	    }
	  public void excluirTutor(int idUsuario) throws SQLException {
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
	    }

	    public Tutor buscarTutorPorEmail(String email) throws SQLException {
	        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
	        Usuario usuario = usuarioDAO.buscarEmail(email);
	        if (usuario == null) return null;

	        String sql = "SELECT area_especializacao FROM Tutor WHERE id_usuario = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setInt(1, usuario.getId());
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                Tutor tutor = new Tutor(
	                    rs.getString("area_especializacao"),
	                    usuario.getId(),
	                    usuario.getRetornaNome(),
	                    usuario.getEmail(),
	                    usuario.getSenha(),
	                    usuario.getBio(),
	                    usuario.getTipoUsuario()
	                );
	                return tutor;
	            }
	        }
	        return null;
	    }
}