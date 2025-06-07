
package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class AlunoDAO {
	private Connection conexao;

	public AlunoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public void inserirAluno(Aluno aluno) throws SQLException {
		String sql = "INSERT INTO Aluno (id_usuario) VALUES (?)";
		try(PreparedStatement stmt = conexao.prepareStatement(sql)){
			stmt.setInt(1, aluno.getId());
			stmt.executeUpdate();
		}
	}
	
	public Aluno buscarPorIdUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT  u.nome, u.email, u.senha, u.bio, u.tipo_usuario, u.salt " +
                     "FROM Aluno a JOIN Usuario u ON a.id_usuario = u.id WHERE a.id_usuario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               Aluno aluno = new Aluno(
                    idUsuario,
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("bio"),
                    rs.getString("tipo_usuario")
                );
                return aluno;
            }
        }
        return null;
    }
	
	
	  public void excluir(int idUsuario) throws SQLException {
	        String sqlAluno = "DELETE FROM Aluno WHERE id_usuario = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sqlAluno)) {
	            stmt.setInt(1, idUsuario);
	            stmt.executeUpdate();
	        }

	        String sqlUsuario = "DELETE FROM Usuario WHERE id = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sqlUsuario)) {
	            stmt.setInt(1, idUsuario);
	            stmt.executeUpdate();
	        }
	    }

	    public Aluno buscarEmail(String email) throws SQLException {
	        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
	        Usuario usuario = usuarioDAO.buscarEmail(email);
	        if (usuario == null) return null;

	        String sql = "SELECT * FROM Aluno WHERE id_usuario = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setInt(1, usuario.getId());
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                Aluno aluno = new Aluno(
	                    usuario.getId(),
	                    usuario.getRetornaNome(),
	                    usuario.getEmail(),
	                    usuario.getSenha(),
	                    usuario.getBio(),
	                    usuario.getTipoUsuario()
	                );
	                return aluno;
	            }
	        }
	        return null;
	    }
}