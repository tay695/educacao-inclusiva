
package com.ifbaiano.educacaoinclusiva.model.dto;

import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

public class SessionDTO{
	 private int id;
	    private String nome;
	    private String email;
	    private TipoDeUsuario tipo;

	    public SessionDTO(Usuario usuario) {
	        this.id = usuario.getId();
	        this.nome = usuario.getRetornaNome();
	        this.email = usuario.getEmail();
	        this.tipo = usuario.getTipo();
	    }


	    public int getId() {
	        return id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public TipoDeUsuario getTipo() {
	        return tipo;
	    }


		public Usuario getUsuario() {
			return null;
		}
}