package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class AlunoDAO {
	private Connection conexao;
	private UsuarioDAO usuarioDAO;

	public AlunoDAO(Connection connection) {
		this.conexao = connection;
		this.usuarioDAO = new UsuarioDAO(connection);
	}

	public void inserirAluno(Aluno aluno) throws SQLException {
		int idGerado = usuarioDAO.inserir(aluno);
		if (idGerado != -1) {
			aluno.setId(idGerado);
			String sql = "INSERT INTO Aluno (id_usuario) VALUES (?)";
			try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
				stmt.setInt(1, idGerado);
				stmt.executeUpdate();
			}

		} else {
			throw new SQLException("Houve um erro ao inserir o novo usuário aluno");
		}
	}

	public Aluno buscarAlunoPorEmail(String email) throws SQLException {
		Usuario usuario = usuarioDAO.buscarEmail(email);
		if (usuario == null) {
			return null;
		}
		String sqlAluno = "SELECT id_usuario FROM Aluno WHERE id_usuario = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlAluno)) {
			stmt.setInt(1, usuario.getId());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Aluno aluno = new Aluno(usuario.getId(), usuario.getRetornaNome(), usuario.getEmail(),
						usuario.getSenha(), usuario.getBio());
				aluno.setSalt(usuario.getSalt());
				return aluno;
			}
		}
		return null;
	}

	public Aluno buscarPorId(int id) throws SQLException {
		Usuario usuario = usuarioDAO.buscarId(id);
		if (usuario == null) {
			return null;
		}

		// Verifica se é aluno
		String sqlAluno = "SELECT id_usuario FROM Aluno WHERE id_usuario = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlAluno)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Aluno aluno = new Aluno(usuario.getId(), usuario.getRetornaNome(), usuario.getEmail(),
						usuario.getSenha(), usuario.getBio());
				aluno.setSalt(usuario.getSalt());
				return aluno;
			}
		}
		return null;
	}

	public void atualizarAluno(Aluno aluno) throws SQLException {
		usuarioDAO.atualizarUsuario(aluno);
	}

	public void excluirAluno(int idUsuario) throws SQLException {
		String sqlAluno = "DELETE FROM Aluno WHERE id_usuario = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sqlAluno)) {
			stmt.setInt(1, idUsuario);
			stmt.executeUpdate();
		}
		usuarioDAO.excluirUsuario(idUsuario);
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
}