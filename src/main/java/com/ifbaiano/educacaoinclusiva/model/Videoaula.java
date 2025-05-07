package com.ifbaiano.educacaoinclusiva.model;

public class Videoaula {
    private int id;
    private String titulo;
    private String url;
    private int idModulo;

    public Videoaula(int id, String titulo, String url, int idModulo) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.idModulo = idModulo;
    }

    public Videoaula(String titulo, String url, int idModulo) {
        this.titulo = titulo;
        this.url = url;
        this.idModulo = idModulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
