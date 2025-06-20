package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ifbaiano.educacaoinclusiva.model.Curso;

import java.sql.Statement;

public class CursoDAO {
	private final Connection conexao;

	public CursoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public Curso addCurso(Curso curso) throws SQLException {
	    String sql = "INSERT INTO Curso (titulo, descricao, tutor_id, area) VALUES (?, ?, ?, ?)";
	    
	    try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, curso.getTitulo());
	        stmt.setString(2, curso.getDescricao());
	        stmt.setInt(3, curso.getTutorId());
	        stmt.setString(4, curso.getArea());
	        
	        stmt.executeUpdate();
	        
	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                curso.setId(rs.getInt(1));
	            }
	        }
	        return curso;
	    }
	}


	public List<Curso> listarCursos() throws SQLException {
		List<Curso> cursos = new ArrayList<>();
		String sql = "SELECT * FROM Curso";


		try (PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Curso curso = new Curso();
				curso.setId(rs.getInt("id"));
				curso.setTitulo(rs.getString("titulo"));
				curso.setDescricao(rs.getString("descricao"));
				curso.setArea(rs.getString("area"));
				cursos.add(curso);
			}
		}

		return cursos;
	}

	public void update(Curso curso) throws SQLException {
		String sql = "UPDATE Curso SET titulo = ?, descricao = ?, area = ? WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, curso.getTitulo());
			stmt.setString(2, curso.getDescricao());
			stmt.setString(3, curso.getArea());
			stmt.setInt(4, curso.getId());
			stmt.executeUpdate();
		}
	}

	public void delete(int idCurso) throws SQLException {
		String sql = "DELETE FROM Curso WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, idCurso);
			stmt.executeUpdate();
		}
	}

	public List<Curso> pesquisarPorTitulo(String titulo) throws SQLException {
		String sql = "SELECT * FROM Curso WHERE titulo = ?";
		List<Curso> cursos = new ArrayList<>();

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, titulo);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Curso curso = new Curso();
					curso.setId(rs.getInt("id"));
					curso.setTitulo(rs.getString("titulo"));
					curso.setDescricao(rs.getString("descricao"));
					curso.setArea(rs.getString("area"));
					cursos.add(curso);
				}
			}
		}

		return cursos;
	}
	public Curso buscarPorId(int cursoId) throws SQLException {
	    String sql = "SELECT * FROM Curso WHERE id = ?";
	    Curso curso = null;
	    
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, cursoId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                curso = new Curso();
	                curso.setId(rs.getInt("id"));
	                curso.setTitulo(rs.getString("titulo"));
	                curso.setDescricao(rs.getString("descricao"));
	                curso.setArea(rs.getString("area"));
	            }
	        }
	    }
	    return curso;
	}
	public List<Curso> listarPorTutor(int tutorId) throws SQLException {
	    List<Curso> cursos = new ArrayList<>();
	    String sql = "SELECT * FROM Curso WHERE tutor_id = ?";

	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, tutorId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Curso curso = new Curso();
	                curso.setId(rs.getInt("id"));
	                curso.setTitulo(rs.getString("titulo"));
	                curso.setDescricao("descricao");
	                curso.setTutorId(tutorId);
	                curso.setArea("area");
	                cursos.add(curso);
	            }
	        }
	    }
	    return cursos;
	}
}