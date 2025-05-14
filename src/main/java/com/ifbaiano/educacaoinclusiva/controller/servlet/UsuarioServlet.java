package com.ifbaiano.educacaoinclusiva.controller.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.Tutor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Cadastro do usuário
@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// campos que serão prennchidos no formulário de cadastro

		String tipoUsuario = request.getParameter("tipoUsuario");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String bio = request.getParameter("bio");
		String dataNascimentoStr = request.getParameter("dataNascimento");
		String areaEspecializacao = request.getParameter("areaEspecializacao");

		java.sql.Date dataNascimento = null;

		try {
			// Usando SimpleDateFormat para adequar ao formato esperdo

			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date utilDate = formato.parse(dataNascimentoStr);
			dataNascimento = new java.sql.Date(utilDate.getTime());
		} catch (java.text.ParseException e) {

			// mensagem que será exibida se a data estiver fora do padrão
			request.setAttribute("erro", "Esta data de nascimento não é válida.");
			request.getRequestDispatcher("/formularioCadastro.jsp").forward(request, response);
			return;
		}

		// Criação do objeto Usuario aluno
		Usuario usuario = null;
		if ("aluno".equalsIgnoreCase(tipoUsuario)) {
			usuario = new Aluno(0, nome, email, senha, bio, dataNascimento);
		} else if ("tutor".equalsIgnoreCase(tipoUsuario)) {
			usuario = new Tutor(areaEspecializacao, 0, nome, email, senha, bio);
		} else {
			request.setAttribute("erro", "Tipo de usuário inválido");
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
			return;
		}

		// salvando no banco de dados
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);

			// Redireciona para a página de login ou dashboard após o cadastro
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao salvar usuário no banco de dados.");
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
		}
	}
}
