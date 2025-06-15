package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
	private int idUsuario;
	private List<VideoAula> videoaulas;
	private List<Curso> favoritos = new ArrayList<>();

	public Aluno(int id, String nome, String email, String senha, String bio, String tipoUsuario) {
		super(nome, email, senha, bio, tipoUsuario);
	}
	
	public Aluno( String nome, String email, String senha, String bio, String tipoUsuario) {
		super( nome, email, senha, bio, tipoUsuario);
	}
	
	public void inscreverAula(VideoAula aula) {
		        videoaulas.add(aula);
		   }
					
	public void listarAulas() {
	    if (videoaulas.isEmpty()) {
	        System.out.println("Você não está inscrito em nenhum curso!");
	    } else {
	        System.out.println("Cursos que " + getRetornaNome() + " está inscrito:");
	        for (VideoAula a : videoaulas) {
	            System.out.println("- " +a.getTitulo());
	        }
	    }
	}

	public void addCursoFavorito(Curso curso) {
		favoritos.add(curso);															 
	}

	@Override
	public void Postar(String conteudo) {
		System.out.println("Comentário adicionado por " + getRetornaNome() + " ");
	}

	public List<VideoAula> getVideoaulas() {
		return videoaulas;
	}

	public void setVideoaulas(List<VideoAula> videoaulas) {
		this.videoaulas = videoaulas;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
