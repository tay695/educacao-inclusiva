package com.ifbaiano.educacaoinclusiva.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

@WebServlet("/CadastroAluno")
public class CadastroAlunoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String bio = request.getParameter("bio");

		if (senhaDigitada == null || senhaDigitada.isEmpty()) {
			request.setAttribute("erro", "A senha não pode estar vazia.");
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
			return;
		}

		String salt = SenhaUtils.gerarSalt();
		String senha = SenhaUtils.gerarHashSenha(senhaDigitada, salt);

		try (Connection conexao = DBConfig.criarConexao()) {
			Aluno aluno = new Aluno(0, nome, email, senha, bio);
			aluno.setSalt(salt);

			AlunoDAO alunoDAO = new AlunoDAO(conexao);
			alunoDAO.inserirAluno(aluno);
			response.sendRedirect("pages/login.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar aluno.");
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
		}
	}
}
