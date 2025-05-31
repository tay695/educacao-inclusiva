package com.ifbaiano.educacaoinclusiva.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.dto.LoginDTO;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;
import com.ifbaino.educacaoinclusiva.utils.validation.Validador;

public class LoginController {

	private final UsuarioDAO usuarioDao;
	private final AlunoDAO alunoDao;
	private final TutorDAO tutorDao;
	private Usuario usuarioAutenticado;

	public LoginController(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
		this.alunoDao = new AlunoDAO(usuarioDao.getConexao());
		this.tutorDao = new TutorDAO(usuarioDao.getConexao());
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

		Usuario usuarioBase = usuarioDao.buscarEmail(email);
		if (usuarioBase == null) {
			erros.add(new ErroCampo("email", email, "Email não encontrado"));
			return erros;
		}

		boolean senhaValida = SenhaUtils.verificarSenha(senhaDigitada, usuarioBase.getSalt(), usuarioBase.getSenha());
		if (!senhaValida) {
			erros.add(new ErroCampo("senha", "", "Senha incorreta"));
			return erros;
		}

	
		Aluno aluno = alunoDao.buscarAlunoPorEmail(email);
		if (aluno != null) {
			this.usuarioAutenticado = aluno;
			return erros;
		}

		Tutor tutor = tutorDao.buscarTutorPorEmail(email);
		if (tutor != null) {
			this.usuarioAutenticado = tutor;
			return erros;
		}
		this.usuarioAutenticado = usuarioBase;
		return erros;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
}
