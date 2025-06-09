package com.ifbaiano.educacaoinclusiva.model;

public class VideoAula {
	private int id;
	private String titulo;
	private String url;
	private Modulo modulo;
	private int idModulo;

	public VideoAula(int id, String titulo, String url, int idModulo) {
		this.id = id;
		this.titulo = titulo;
		setUrl(url);
		this.idModulo = idModulo;
	}

	public VideoAula() {
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
		if (url == null || url.trim().isEmpty()) {
			throw new IllegalArgumentException("URL não pode ser vazia");
		}
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

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

}
