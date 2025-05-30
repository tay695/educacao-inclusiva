package com.ifbaiano.educacaoinclusiva.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.dto.LoginDTO;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;
import com.ifbaino.educacaoinclusiva.utils.validation.Validador;

public class LoginController {

	private final UsuarioDAO usuarioDao;
	private Usuario usuarioAutenticado;

	public LoginController(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
		this.usuarioAutenticado = null;
	}

	public List<ErroCampo> autenticar(LoginDTO loginDTO) throws SQLException {
        List<ErroCampo> erros = new ArrayList<>();

        String email = loginDTO.getEmail();
        String senhaDigitada = loginDTO.getSenha();

        Validador.notBlank(email, "email", erros);
        Validador.notBlank(senhaDigitada, "senha", erros);

        if (!erros.isEmpty()) {
            return erros;
        }

        Usuario usuario = usuarioDao.buscarEmail(email);
        if (usuario == null) {
            System.out.println("Email não encontrado no banco: " + email);
            erros.add(new ErroCampo("email", email, "Email não encontrado"));
            return erros;
        }

        boolean senhaValida = SenhaUtils.verificarSenha(senhaDigitada, usuario.getSalt(), usuario.getSenha());
        if (senhaValida) {
        	System.out.println("Login bem-sucedido para: " + email);
            this.usuarioAutenticado = usuario;
        } else {
            System.out.println("Erro de senha para o email: " + email);
            this.usuarioAutenticado = usuario;
        }
        
		return erros;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
}
