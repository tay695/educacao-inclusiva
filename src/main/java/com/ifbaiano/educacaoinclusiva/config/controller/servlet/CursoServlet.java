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

@WebServlet("/curso")
public class CursoServlet extends HttpServlet {

	private Connection conexao;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String area = request.getParameter("area");

		Curso curso = new Curso();
		curso.setTitulo(titulo);
		curso.setDescricao(descricao);

		curso.setArea(area);
		try (Connection conexao = DBConfig.criarConexao()) {
			CursoDAO cursoDAO = new CursoDAO(conexao);

				cursoDAO.addCurso(curso);
				response.sendRedirect("curso");
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection conexao = DBConfig.criarConexao()) {

		CursoDAO cursoDAO = new CursoDAO(conexao);
			List<Curso> cursos = cursoDAO.getCursos();
			request.setAttribute("cursos", cursos);
			request.getRequestDispatcher("/pages/listaCursos.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("Erro ao buscar cursos", e);
		}
	}
}
