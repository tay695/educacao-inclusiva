package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

@WebServlet("/cadastroAluno")
public class CadastroAlunoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String bio = request.getParameter("bio");

		try (Connection conexao = DBConfig.criarConexao()) {
			String salt = SenhaUtils.gerarSalt();
			String hash = SenhaUtils.gerarHash(senhaDigitada + salt);
			
			// Inserir o usuário
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			Usuario usuario = new Usuario(0, nome, email, senhaDigitada, bio);
			usuario.setSalt(salt);

			int idUsuario = usuarioDAO.inserir(usuario);
			if (idUsuario == -1) {
				throw new SQLException("Falha ao inserir usuário.");
			}

			// Inserir aluno com id_usuario correto
			Aluno aluno = new Aluno(idUsuario, nome, email, senhaDigitada, bio);
			aluno.setSalt(salt);

			AlunoDAO alunoDAO = new AlunoDAO(conexao);
			alunoDAO.inserirAluno(aluno);

			System.out.println("Cadastro realizado para: " + email);
			response.sendRedirect("pages/login.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar aluno.");
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
	}
}
