package com.ifbaiano.educacaoinclusiva.model;

public class VideoAula {
	private int id;
	private String titulo;
	private String url;
	private Modulo modulo;
	private int moduloId;

	public VideoAula(int id, String titulo, String url, int moduloId) {
		this.id = id;
		this.titulo = titulo;
		setUrl(url);
		this.moduloId = moduloId;
	}
	public VideoAula(int id, String titulo, String url) {
		this.id = id;
		this.titulo = titulo;
		setUrl(url);
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

	public int getModuloId() {
		return moduloId;
	}

	public void setmoduloId(int moduloId) {
		this.moduloId = moduloId;
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
	public void ADD(VideoAula aula) {
		aula.ADD(aula);
	}
}
