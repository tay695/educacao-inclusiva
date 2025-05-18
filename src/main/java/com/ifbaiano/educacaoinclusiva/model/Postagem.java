package com.ifbaiano.educacaoinclusiva.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Postagem{
    private String titulo;
    private String autor;
    private String conteudo;
    private int id;
    private LocalDateTime dataHora;
    private Curso curso; 
    private Modulo modulo;
    private List<String> comentarios = new ArrayList<>();

    public Postagem(String titulo, String autor, String conteudo, int id, LocalDateTime dataHora, Curso curso, Modulo modulo){
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
        this.id = id;
        this.dataHora = dataHora;
        this.curso = curso;
        this.modulo = modulo;
    }   
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getConteudo(){
        return conteudo;
    }
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDataHora(){
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }
    public Curso getCurso(){
        return curso;
    }
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    public Modulo getModulo() {
         return modulo; 
    }
    public void setModulo(Modulo modulo) {
        this.modulo = modulo; 
    }
    public List<String> getComentarios(){
        return comentarios;
    }
    public void adicionarComentario (String comentario){
        comentarios.add(comentario);
    }
}