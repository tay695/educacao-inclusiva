package com.ifbaiano.educacaoinclusiva.model;

import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private String bio;
	private String avaliacao;
	private String tipoUsuario;
	private TipoDeUsuario tipo;
	
	
	public Usuario(int id, String nome, String email, String senha, String bio, String tipoUsuario ) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.bio = bio;
		this.setTipoUsuario(tipoUsuario);
	}

	public Usuario( String nome, String email, String senha, String bio, String tipoUsuario ) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.bio = bio;
		this.setTipoUsuario(tipoUsuario);
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRetornaNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}


	public void Postar(String conteudo) {
		System.out.println("Esperando postagem");
	}

	public TipoDeUsuario getTipo() {
		return (TipoDeUsuario) tipo;
	}

	public void setTipo(TipoDeUsuario tipo) {
		this.tipo = tipo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}

