package com.ifbaiano.educacaoinclusiva.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

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
		this.salt = gerarSalt();
		this.senha = aplicarHash(senha, this.salt);

	}

	public String gerarSalt() {
		SecureRandom random = new SecureRandom();
		byte[] saltBytes = new byte[16];
		random.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}

	private String aplicarHash(String senha, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			String texto = senha + salt;
			byte[] hashBytes = digest.digest(texto.getBytes());

			
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro ao aplicar hash: " + e.getMessage());
		}
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

	public void posta(String conteudo) {
		setAvaliacao(conteudo);
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
