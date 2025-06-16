package com.ifbaiano.educacaoinclusiva.model.dto;

import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class SessionDTO {

	private Connection conexao;

	private static SessionDTO instance;
	private String tipo; 

	private Usuario usuarioLogado;
	private Aluno alunoLogado;
	private Tutor tutorLogado;

	private SessionDTO() {
	}

	public SessionDTO(Connection conexao) {
		this.conexao = conexao;
	}

	public static SessionDTO getInstance() {
		if (instance == null) {
			instance = new SessionDTO();
		}
		return instance;
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
		this.alunoLogado = null;
		this.tutorLogado = null;
	}

	public void setUsuarioAluno(Aluno aluno) {
		this.alunoLogado = aluno;
		this.usuarioLogado = aluno;
	}

	public void setUsuarioTutor(Tutor tutor) {
		this.tutorLogado = tutor;
		this.usuarioLogado = tutor;

		if (tutor != null && usuarioLogado != null) {
			usuarioLogado.setId(tutor.getIdUsuario());
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public Aluno getAlunoLogado() {
		return alunoLogado;
	}

	public Tutor getTutorLogado() {
		if (tutorLogado == null && usuarioLogado != null && "tutor".equalsIgnoreCase(usuarioLogado.getTipoUsuario())) {
			try {
				TutorDAO tutorDAO = new TutorDAO(conexao);
				tutorLogado = tutorDAO.buscarPorIdUsuario(usuarioLogado.getId());
			} catch (SQLException e) {
				System.err.println("Erro ao carregar tutor: " + e.getMessage());
			}
		}
		return tutorLogado;
	}

	public void logout() {
		this.usuarioLogado = null;
		this.alunoLogado = null;
		this.tutorLogado = null;
	}

	public boolean isLoggedIn() {
		return usuarioLogado != null;
	}

	public boolean isAluno() {
		return alunoLogado != null;
	}

	public boolean isTutor() {
		return tutorLogado != null;
	}

	public int getId() {
		return usuarioLogado != null ? usuarioLogado.getId() : 0;
	}

	public String getNome() {
		return usuarioLogado != null ? usuarioLogado.getRetornaNome() : "";
	}

	public String getEmail() {
		return usuarioLogado != null ? usuarioLogado.getEmail() : "";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
