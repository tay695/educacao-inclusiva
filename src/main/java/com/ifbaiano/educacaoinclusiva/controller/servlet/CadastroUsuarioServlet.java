package com.ifbaiano.educacaoinclusiva.controller.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaino.educacaoinclusiva.utils.SenhaUtils;
import com.ifbaiano.educacaoinclusiva.model.Tutor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Cadastro do usuário
@WebServlet("/CadastroUsuario")
public class CadastroUsuarioServlet extends HttpServlet {
	// apenas uma boa prática , mecanismo de serialização do java, o eclipse ficando
	// dando um alerta ai adicionei a sugestão
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tipoUsuario = request.getParameter("tipoUsuario");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String bio = request.getParameter("bio");
		String dataNascimentoStr = request.getParameter("dataNascimento");
		String areaEspecializacao = request.getParameter("areaEspecializacao");
		java.sql.Date dataNascimento = null;

		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = formato.parse(dataNascimentoStr);
			dataNascimento = new java.sql.Date(utilDate.getTime());

		} catch (java.text.ParseException e) {
			request.setAttribute("erro", "Esta data de nascimento não é válida.");
			request.getRequestDispatcher("/formularioCadastro.jsp").forward(request, response);
			return;
		}
		// Gerar salt e hash da senha
		String salt = SenhaUtils.gerarSalt();
		String senha = SenhaUtils.gerarHashSenha(senhaDigitada, salt);

		Usuario usuario = null;
		if ("aluno".equalsIgnoreCase(tipoUsuario)) {
			usuario = new Aluno(0, nome, email, senha, bio, dataNascimento);
		}
		if  ("tutor".equalsIgnoreCase(tipoUsuario)) {
			usuario = new Tutor(areaEspecializacao, 0, nome, email, senha, bio);
		} else {
			request.setAttribute("erro", "Tipo de usuário inválido");
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
			return;
		}

		usuario.setSalt(salt);

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao salvar usuário no banco de dados." + e);
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
		}
	}
}