package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.CursoDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Curso;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/curso")
public class CursoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String area = request.getParameter("area");

		if (titulo == null || titulo.trim().isEmpty()) {
			session.setAttribute("erro", "Título é obrigatório");
			response.sendRedirect(request.getContextPath() + "/curso?action=novo");
			return;
		}

		Curso curso = new Curso();
		curso.setTitulo(titulo);
		curso.setDescricao(descricao != null ? descricao : "");
		curso.setArea(area != null ? area : "");

		try (Connection conexao = DBConfig.criarConexao()) {
			CursoDAO cursoDAO = new CursoDAO(conexao);
			curso = cursoDAO.addCurso(curso); // Assume que addCurso retorna o curso com ID

			session.setAttribute("mensagem", "Curso criado com sucesso!");
			response.sendRedirect(request.getContextPath() + "/curso?id=" + curso.getId());

		} catch (SQLException e) {
			session.setAttribute("erro", "Erro ao criar curso: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/curso?action=novo");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String idStr = request.getParameter("id");

		try (Connection conexao = DBConfig.criarConexao()) {
			CursoDAO cursoDAO = new CursoDAO(conexao);

			if ("novo".equals(action)) {
				request.getRequestDispatcher("/pages/novoCurso.jsp").forward(request, response);
				return;
			}

			if (idStr != null) {
				Curso curso = cursoDAO.buscarPorId(Integer.parseInt(idStr));
				request.setAttribute("curso", curso);
				request.getRequestDispatcher("/pages/verCurso.jsp").forward(request, response);
			} else {
				List<Curso> cursos = cursoDAO.listarCursos();
				request.setAttribute("cursos", cursos);
				request.getRequestDispatcher("/pages/listaCursos.jsp").forward(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("erro", "Erro: " + e.getMessage());
			request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
		}
	}
}