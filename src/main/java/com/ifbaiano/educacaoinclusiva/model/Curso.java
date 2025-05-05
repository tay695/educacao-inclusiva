package com.ifbaiano.educacaoinclusiva.model;

import java.util.List;

public class Curso {
	private int id;
	private String titulo;
	private String descricao;
	private String area;
	private Modulo modulo;
	private List<Postagem> postagem;

	public Curso(int id, String titulo, String descricao, String area, Modulo modulo, List<Postagem> postagem) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.area = area;
		this.modulo = modulo;
		this.postagem = postagem;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Postagem> getPostagems() {
		return postagem;
	}

	public void setPostagems(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	public Modulo retornaModulo() {
		
		return modulo;
	}
}