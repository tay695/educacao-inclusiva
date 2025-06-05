package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarTutor")
public class TutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String bio = request.getParameter("bio");
		String area = request.getParameter("area");

		try (Connection conexao = DBConfig.criarConexao()) {
			String salt = SenhaUtils.gerarSalt();
			String senhaHasheada = SenhaUtils.gerarHash(senhaDigitada);

			Tutor tutor = new Tutor(area,0, nome, email, senhaHasheada, bio);
			tutor.setSalt(salt);

			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			int idGerado = usuarioDAO.inserir(tutor); 

			if (idGerado > 0) {
				tutor.setId(idGerado); 
				TutorDAO tutorDAO = new TutorDAO(conexao);
				tutorDAO.adicionarTutor(tutor);
				System.out.println("Tutor cadastrado com sucesso: " + email);
				response.sendRedirect("pages/login.jsp");
			} else {
				throw new SQLException("Falha ao inserir usuário base.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar tutor: " + e.getMessage());
			request.getRequestDispatcher("pages/cadastroTutor.jsp").forward(request, response);
		}
	}
}

