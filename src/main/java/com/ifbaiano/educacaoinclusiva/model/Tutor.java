package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Usuario {
	private int idUsuario;
    private String areaEspecializacao;
    private List<VideoAula> videoaulas;
    List<Modulo> modulos = new ArrayList<>();


    public Tutor(String areaEspecializacao, int id, String nome, String email, String senha, String bio,String tipoUsuario) {
    	super(nome, email, senha, bio, tipoUsuario);
    	this.areaEspecializacao = areaEspecializacao;
    	this.videoaulas = new ArrayList<>();
    }

    public Tutor(String areaEspecializacao, String nome, String email, String senha, String bio,String tipoUsuario) {
    	super( nome, email, senha, bio, tipoUsuario);
    	this.areaEspecializacao = areaEspecializacao;
    }
public Tutor() {
	
}
    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }

    public void setAreaEspecializacao(String areaEspecializacao) {
        this.areaEspecializacao = areaEspecializacao;
    }

    public void postarVideoaula(int id, String titulo, String url, int idModulo) {
        VideoAula video = new VideoAula(id, titulo, url, idModulo);
        videoaulas.add(video);
        System.out.println("Videoaula postada com sucesso: " + titulo);
    }
    public List<VideoAula> getVideoaulas(){
        return videoaulas;
    }
    public void setVideoaulas(List<VideoAula> videoaulas){
        this.videoaulas = videoaulas;
    }
    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }
    public int getId() {
        return idUsuario;
    }
}
