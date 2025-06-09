package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Curso;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;

@WebServlet("/dashboardAluno")
public class AlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conexao;

    private AlunoDAO alunoDAO = new AlunoDAO(conexao);

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
        SessionDTO sessionDTO = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (sessionDTO == null || !(sessionDTO.getUsuario() instanceof Aluno)) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        Aluno aluno = (Aluno) sessionDTO.getUsuario();
        List<Curso> cursosInscritos = null;
		try {
			cursosInscritos = alunoDAO.buscarCursosDoAluno(aluno.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for (Curso curso : cursosInscritos) {
            aluno.inscreverEmCurso(curso);         }

        // Atualiza o objeto na sessão
        sessionDTO.setUsuario(aluno);
        request.getSession().setAttribute("usuarioLogado", sessionDTO);

        // Encaminha para a página do aluno
        request.setAttribute("cursos", cursosInscritos);
        request.getRequestDispatcher("/pages/dashboardAluno.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
