package com.ifbaiano.educacaoinclusiva.model;

import java.util.List;

public class Modulo {
    private int id;
    private String titulo;
    private String descricao;
    private List<String> videoaulas;

    public Modulo(int id, String titulo, String descricao, List<String> videoaulas){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.videoaulas = videoaulas;
    }

    public Modulo(String titulo, String descricao, List<String> videoaulas){
        this.titulo = titulo;
        this.descricao = descricao;
        this.videoaulas = videoaulas;
    }
 
    public void adicionarVideoaula(String videoaula) {
        videoaulas.add(videoaula);
    }

    public boolean removerVideoaula(String videoaula) {
        return videoaulas.remove(videoaula);
    }

    public void listarVideoaulas() {
        System.out.println("Videoaulas em \"" + titulo + "\":");
        for (String v : videoaulas) {
            System.out.println("- " + v);
        }
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
    public List<String> getVideoaulas() {
        return videoaulas;
    }
    public void setVideoaulas(List<String> videoaulas) {
        this.videoaulas = videoaulas;
    }
}