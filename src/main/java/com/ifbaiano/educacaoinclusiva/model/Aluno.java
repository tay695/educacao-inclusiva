package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
	private List<Curso> cursosInscritos = new ArrayList<>();
	private List<Curso> favoritos = new ArrayList<>();

	public Aluno(int id, String nome, String email, String senha, String bio) {
		super(id, nome, email, senha, bio);
	}
	
	public void adicionarCurso(Curso curso) {
		cursosInscritos.add(curso);	
	}
	public void listaCurso() {
		if(cursosInscritos.isEmpty()){
			System.out.println("Você não está inscrito em nenhum curso!");
		}else {		
			System.out.println("Curso que" +  getRetornaNome() + " está inscrito" );
			// adicionar quando a classe curso estiver completa getNomeCurso;
			for(Curso curso: cursosInscritos) {
				System.out.println("MEUS CURSOS  ");
				System.out.println("--"); // adicionar quando a classe curso estiver pronta + getnomeCurso());
			}
		}
	}
	public void addCursoFavoritou(Curso curso) {
		favoritos.add(curso);
	}
	@Override
	public void Postar(String conteudo) {
		System.out.println("Comentário adicionado por " + getRetornaNome() + " ");
	}
}
