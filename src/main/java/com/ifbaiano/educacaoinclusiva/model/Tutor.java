package com.ifbaiano.educacaoinclusiva.model;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Usuario {
    private String areaEspecializacao;
    private List<VideoAula> videoaulas;

    public Tutor(String areaEspecializacao, int id, String nome, String email, String senha, String bio) {
        super(id, nome, email, senha, bio);
        this.areaEspecializacao = areaEspecializacao;
        this.videoaulas = new ArrayList<>();
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
    public List<VideoAula> getVideoAulas(){
        return this.videoaulas;
    }
}
