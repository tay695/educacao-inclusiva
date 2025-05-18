package com.ifbaiano.educacaoinclusiva.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.CursoDAO;
import com.ifbaiano.educacaoinclusiva.model.Curso;

import java.io.IOException;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/curso")
public class CursoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

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

	
	    CursoDAO cursoDAO = new CursoDAO();
	    cursoDAO.addCurso(curso);

	    
	    response.sendRedirect("curso"); 
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    List<Curso> cursos = CursoDAO.getCursso();
	    request.setAttribute("cursos", cursos);
	    request.getRequestDispatcher("/pages/listaCursos.jsp").forward(request, response);
	}

	
}
