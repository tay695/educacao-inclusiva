package com.ifbaino.educacaoinclusiva.utils.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListErrors {
	    private List<ErroCampo> erros;

	    public ListErrors() {
	        this.erros = new ArrayList<>();
	    }

	  
	    public void adicionar(ErroCampo erro) {
	        erros.add(erro);
	    }

	    public List<ErroCampo> getErros() {
	        return erros;
	    }

	    
	    public int tamanho() {
	        return erros.size();
	    }

	 
	    public boolean estaVazia() {
	        return erros.isEmpty();
	    }

	  
	    public List<ErroCampo> buscarPorCampo(String nomeCampo) {
	        return erros.stream()
	                .filter(e -> e.getNomeCampo().equalsIgnoreCase(nomeCampo))
	                .collect(Collectors.toList());
	    }

	  //mensagem de erronpas os campos correpondentes 
	    public String mensagemDoCampo(String nomeCampo) {
	        List<ErroCampo> encontrados = buscarPorCampo(nomeCampo);
	        StringBuilder mensagens = new StringBuilder();

	        for (ErroCampo erro : encontrados) {
	            mensagens.append("Campo: ").append(erro.getNomeCampo())
	                     .append(" - ").append(erro.getMensagemErro()).append("\n");
	        }

	        return mensagens.toString();
	    }
	}


