package com.ifbaiano.educacaoinclusiva.model;

public class Inscricao {
	private int id;
	private int idUsuario;
	private int idVideoAula;

	public Inscricao() {

	}

	public Inscricao(int idUsuario, int idVideoAula) {
		this.idUsuario = idUsuario;
		this.idVideoAula = idVideoAula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdVideoAula() {
		return idVideoAula;
	}

	public void setIdVideoAula(int idVideoAula) {
		this.idVideoAula = idVideoAula;
	}

}
