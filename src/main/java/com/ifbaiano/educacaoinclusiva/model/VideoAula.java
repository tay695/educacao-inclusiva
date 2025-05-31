package com.ifbaiano.educacaoinclusiva.model;

public class VideoAula {
	private int id;
	private String titulo;
	private String url;
	private int idModulo;

	public VideoAula(int id, String titulo, String url, int idModulo) {
		this.setId(id);
		this.titulo = titulo;
		this.url = url;
		this.idModulo = idModulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
