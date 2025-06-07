package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario {
	private List<Curso> cursosInscritos = new ArrayList<>();
	private List<Curso> favoritos = new ArrayList<>();

	public Aluno(int id, String nome, String email, String senha, String bio, String tipoUsuario) {
		super(id, nome, email, senha, bio, tipoUsuario);
	}
	public Aluno( String nome, String email, String senha, String bio, String tipoUsuario) {
		super( nome, email, senha, bio, tipoUsuario);
	}
	public void adicionarCurso(Curso curso) {
		cursosInscritos.add(curso);
	}

	public void listaCurso(Curso curso) {
		if (cursosInscritos.isEmpty()) {
			System.out.println("Você não está inscrito em nenhum curso!");
		} else {
			System.out.println("Curso que" + getRetornaNome() + " está inscrito" + curso.getTitulo());

			for (Curso cursos : cursosInscritos) {
				System.out.println("MEUS CURSOS  ");
				System.out.println("--" + cursos.getTitulo());
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
}
