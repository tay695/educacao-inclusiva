package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;

import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionDTO sessionDTO = (SessionDTO) request.getSession().getAttribute("usuarioLogado");

		if (sessionDTO == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		TipoDeUsuario tipo = sessionDTO.getTipo();

		if (tipo == TipoDeUsuario.tutor) {
			request.getRequestDispatcher("/WEB-INF/tutor/dashboard-tutor.jsp").forward(request, response);
		} else if (tipo == TipoDeUsuario.aluno) {
			request.getRequestDispatcher("/WEB-INF/aluno/dashboard-aluno.jsp").forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
