package com.ifbaiano.educacaoinclusiva.model;

import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private String bio;
	private String avaliacao;
	private String salt;
	
	
	public Usuario(int id, String nome, String email, String senha, String bio) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.bio = bio;
	}

	public String gerarSalt() {
	    return SenhaUtils.gerarSalt();
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	public void Postar(String conteudo) {
		System.out.println("Esperando postagem");
	}

}

