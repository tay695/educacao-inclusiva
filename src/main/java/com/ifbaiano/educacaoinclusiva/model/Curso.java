package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
	private int id;
	private String titulo;
	private String descricao;
	private String area;
	private Modulo modulo;
	private List<Postagem> postagens;

	public Curso() {
		
	}
	public Curso(int id, String titulo, String descricao, String area, Modulo modulo, List<Postagem> postagem) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.area = area;
		this.modulo = modulo;
		this.postagens = postagem != null? postagens : new ArrayList<>(); //se postagem for null crie uma nova lista vazia (se postagem for null daria erro no codigo, mas se a lista já existe, mesmo que vazia, esse erro não acontece)
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

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}
	public Modulo retornaModulo() {
		return modulo;
	}

	//metodo para adicionar uma nova postagem no curso
	public void adicionarPostagem(Postagem postagem){
		if(this.postagens == null){
			this.postagens = new ArrayList<>();
		}
		this.postagens.add(postagem);
	}
}