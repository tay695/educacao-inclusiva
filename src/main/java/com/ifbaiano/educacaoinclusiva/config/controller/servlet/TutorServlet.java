package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarTutor")
public class TutorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String bio = request.getParameter("bio");
		String salt = "saltGeradoAqui";
		String avaliacao = "";
		String area = request.getParameter("area");

		Tutor tutor = new Tutor(salt, 0, nome, email, senha, bio);
		tutor.setAvaliacao(avaliacao);
		tutor.setAreaEspecializacao(area);

		try (Connection conexao = DBConfig.getConnection()) {
			TutorDAO tutorDAO = new TutorDAO(conexao);
			tutorDAO.adicionarTutor(tutor);
			response.sendRedirect(".pages/login.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar tutor: " + e.getMessage());
			request.getRequestDispatcher("pages/cadastroTutor.jsp").forward(request, response);
		}
	}
}
