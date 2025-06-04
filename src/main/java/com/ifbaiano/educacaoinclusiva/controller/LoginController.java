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
	    System.out.println("Construtor LoginController, usuarioDao é null? " + (usuarioDao == null));

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

		System.out.println("Verificando usuário base para email: " + email);
		Usuario usuarioBase = usuarioDao.buscarEmail(email);

		Usuario usuarioBase1 = usuarioDao.buscarEmail(email);
		if (usuarioBase1 == null) {
		    System.out.println("Usuário base não encontrado");
		    erros.add(new ErroCampo("email", email, "Email não encontrado"));
		    return erros;
		}
		System.out.println("Usuário base encontrado: " + usuarioBase1.getRetornaNome());

		boolean senhaValida = SenhaUtils.verificarSenha(
		    senhaDigitada,
		    usuarioBase1.getSalt(),
		    usuarioBase1.getSenha()
		);
		System.out.println("Senha válida? " + senhaValida);
		if (!senhaValida) {
		    erros.add(new ErroCampo("senha", "", "Senha incorreta"));
		    return erros;
		}

		Aluno aluno = alunoDao.buscarAlunoPorEmail(email);
		System.out.println("Aluno encontrado: " + (aluno != null ? aluno.getRetornaNome() : "nenhum"));
		if (aluno != null) {
		    this.usuarioAutenticado = aluno;
		    return erros;
		}

		Tutor tutor = tutorDao.buscarTutorPorEmail(email);
		System.out.println("Tutor encontrado: " + (tutor != null ? tutor.getRetornaNome() : "nenhum"));
		if (tutor != null) {
		    this.usuarioAutenticado = tutor;
		    return erros;
		}
		return erros;
	}

	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}
}
