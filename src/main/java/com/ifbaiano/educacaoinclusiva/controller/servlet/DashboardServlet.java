package com.ifbaiano.educacaoinclusiva.controller.servlet;

import java.io.IOException;

import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		if (session.getAttribute("usuarioLogado") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		// verfificações para identificar se é aluno ou tutor
		if (usuario instanceof Tutor) {
			request.getRequestDispatcher("/WEB-INF/tutor/dashboard-tutor.jsp").forward(request, response);
		}
		if (usuario instanceof Aluno) {
			request.getRequestDispatcher("WEB-INF/aluno/dashboard-aluno.jsp").forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}

	}
}