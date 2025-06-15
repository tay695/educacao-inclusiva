package com.ifbaiano.educacaoinclusiva.model.dto;

import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

public class SessionDTO {
	private static SessionDTO instance;
	private Usuario usuarioLogado;
	private Aluno alunoLogado;
	private Tutor tutorLogado;

    private SessionDTO() {}


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

	public TipoDeUsuario getTipo() {
		return usuarioLogado != null ? usuarioLogado.getTipo() : null;
	}
}