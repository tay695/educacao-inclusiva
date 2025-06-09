package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
	private int id;
	private String titulo;
	private String descricao;
	private String area;
	private List<Modulo> modulos;
	private List<Postagem> postagens;

	public Curso() {
		this.setModulos(new ArrayList<>());
		this.postagens = new ArrayList<>();
	}

	public Curso(int id, String titulo, String descricao, String area, List<Modulo> modulos, List<Postagem> postagens) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.area = area;
		this.setModulos(modulos != null ? modulos : new ArrayList<>());
		this.postagens = postagens != null ? postagens : new ArrayList<>();
	}

	public void adicionarModulo(Modulo modulo) {
		if (modulos == null) {
			modulos = new ArrayList<>();
		}
		modulos.add(modulo);
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

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

	public void adicionarPostagem(Postagem postagem) {
		if (this.postagens == null) {
			this.postagens = new ArrayList<>();
		}
		this.postagens.add(postagem);
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
}