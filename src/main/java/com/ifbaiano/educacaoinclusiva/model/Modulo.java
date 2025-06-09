package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Modulo {
	private int id;
	private String titulo;
	private String descricao;
	private List<VideoAula> videoAulas;
	private List<VideoAula> videoaulas;
	private List<Postagem> postagens = new ArrayList<>();

	public Modulo() {
		this.videoaulas = new ArrayList<>();
		this.postagens = new ArrayList<>();
	}

	public Modulo(int id, String titulo, String descricao) {
		this.id = id;
		this.setTitulo(titulo);
		this.setDescricao(descricao);
	}

	

	public void adicionarVideoAula(VideoAula videoAula) {
		videoAula.setIdModulo(this.id);
		this.videoAulas.add(videoAula);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	public List<VideoAula> getVideoaulas() {
		return videoaulas;
	}

	public void setVideoaulas(List<VideoAula> videoaulas) {
		this.videoaulas = videoaulas;
	}

	public void adicionarPostagem(Postagem postagem) {
		postagem.setModulo(this);
		postagens.add(postagem);
	}

	public List<Postagem> getPostagems() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
	public List<VideoAula> getVideoAulas() {
		return videoAulas;
	}

	public void setVideoAulas(List<VideoAula> videoAulas) {
		this.videoAulas = videoAulas;
	}

	
}
