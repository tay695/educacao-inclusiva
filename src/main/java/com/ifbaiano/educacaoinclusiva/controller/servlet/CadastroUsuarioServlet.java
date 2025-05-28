package com.ifbaiano.educacaoinclusiva.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { resp.sendRedirect("/pages/login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String tipoUsuario = request.getParameter("tipoUsuario");
		String areaEspecializacao = request.getParameter("areaEspecializacao");
		String bio = request.getParameter("bio");
		String senhaDigitada = request.getParameter("senha");
		
	    if (senhaDigitada == null || senhaDigitada.isEmpty()) {
	        request.setAttribute("erro", "A senha não pode estar vazia.");
	        request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
	        return;
	    }
		String salt = SenhaUtils.gerarSalt();
		String senha = SenhaUtils.gerarHashSenha(senhaDigitada, salt);

		try {
			Connection conexao = DBConfig.criarConexao();

			if ("aluno".equalsIgnoreCase(tipoUsuario)) {
				Aluno aluno = new Aluno(0, nome, email, senha, bio);
				aluno.setSalt(salt);

				AlunoDAO alunoDAO = new AlunoDAO(conexao);
				alunoDAO.inserirAluno(aluno);
				response.sendRedirect("pages/login.jsp");

			} else if ("tutor".equalsIgnoreCase(tipoUsuario)) {
				Tutor tutor = new Tutor(areaEspecializacao, 0, nome, email, senha, bio);
				tutor.setSalt(salt);
				response.sendRedirect("pages/login.jsp");
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar usuário.");
			request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
		}
		
	}
	
}