package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Modulo {
    private int id;
    private String titulo;
    private String descricao;
    private int idCurso;
    private List<VideoAula> videoaulas;

    //lista as postagens relacionadas ao modulo
    private List<Postagem> postagens = new ArrayList<>();

    public Modulo(int id, String titulo, String descricao, int idCurso) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.idCurso = idCurso;
    }

    public Modulo(int id, String titulo, String descricao, List<VideoAula> videoaulas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.videoaulas = videoaulas;
    }

	public Modulo() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public List<VideoAula> getVideoaulas() {
        return videoaulas;
    }

    public void setVideoaulas(List<VideoAula> videoaulas) {
        this.videoaulas = videoaulas;
    }
    //adicionando uma postagem ao modulo
    public void adicionarPostagem(Postagem postagem){
        postagem.setModulo(this);//associa o modulo a postagem
        postagens.add(postagem);
    }

    public List<Postagem> getPostagems(){
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens){
        this.postagens = postagens;
    }
}

