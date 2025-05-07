package com.ifbaiano.educacaoinclusiva.model;

public class Tutor extends Usuario {
	private String areaEspecializacao;

	public Tutor(String areaEspecializacao, int id, String nome, String email, String senha, String bio) {
		super(id, nome, email, senha, bio);
		this.areaEspecializacao = areaEspecializacao;
	}

	public void setAreaEspecializacao(String areaEspecializacao) {
		this.areaEspecializacao = areaEspecializacao;
	}

	public String getAreaEspecializacao() {
		return areaEspecializacao;
	}
}
