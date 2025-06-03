package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

@WebServlet("/CadastroTutor")
public class CadastroTutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String bio = request.getParameter("bio");
		String areaEspecializacao = request.getParameter("areaEspecializacao");

		try (Connection conexao = DBConfig.criarConexao()) {
			// Gerar salt e hash da senha
			String salt = SenhaUtils.gerarSalt();
			String senhaHasheada = SenhaUtils.gerarHashSenha(senhaDigitada, salt);

			// Criar tutor com senha hasheada e salt
			Tutor tutor = new Tutor(areaEspecializacao, 0, nome, email, senhaHasheada, bio);
			tutor.setSalt(salt);

			// Inserir no banco
			TutorDAO tutorDAO = new TutorDAO(conexao);
			tutorDAO.adicionarTutor(tutor);

			response.sendRedirect("pages/login.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar tutor.");
			request.getRequestDispatcher("/pages/cadastroTutor.jsp").forward(request, response);
		}
		System.out.println("Cadastro realizado para: " + email);
	}
}
