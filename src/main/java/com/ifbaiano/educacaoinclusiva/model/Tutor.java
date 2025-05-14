package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Usuario {
	private String areaEspecializacao;
	private List<Videoaula> videoaulas;

	public Tutor(String areaEspecializacao, int id, String nome, String email, String senha, String bio) {
		super(id, nome, email, senha, bio);
		this.areaEspecializacao = areaEspecializacao;
		   this.videoaulas = new ArrayList<>();
	}

	public void setAreaEspecializacao(String areaEspecializacao) {
		this.areaEspecializacao = areaEspecializacao;
	}

	public String getAreaEspecializacao() {
		return areaEspecializacao;
	}

    public void postarVideoaula(String titulo, String url, int idModulo) {
        int novoId = videoaulas.size() + 1;
        Videoaula video = new Videoaula(novoId, titulo, url, idModulo);
        videoaulas.add(video);
        System.out.println("Videoaula postada com sucesso: " + titulo);
    }
}
