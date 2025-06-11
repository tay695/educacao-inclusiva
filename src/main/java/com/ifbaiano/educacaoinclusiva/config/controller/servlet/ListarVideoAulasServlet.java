package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

@WebServlet("/listarVideoAulas")
public class ListarVideoAulasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Aluno aluno = (Aluno) session.getAttribute("usuarioLogado");

		if (aluno == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try (Connection conexao = DBConfig.criarConexao()) {
			VideoAulaDAO aula = new VideoAulaDAO(conexao);
			List<VideoAula> aulasDisponiveis = aula.listarPorAulas();
            System.out.println("Número de aulas encontradas: " + aulasDisponiveis.size());
            for (VideoAula aula1 : aulasDisponiveis) {
                System.out.println("Aula: " + aula1.getTitulo() + " - URL: " + aula1.getUrl());
            }
            
            request.setAttribute("aulasDisponiveis", aulasDisponiveis);
            request.getRequestDispatcher("/pages/DashboardAluno.jsp").forward(request, response);
            
        } catch (SQLException e) {
            // Log do erro
            System.err.println("Erro ao listar aulas: " + e.getMessage());
            e.printStackTrace();
            
            session.setAttribute("erro", "Erro ao carregar as aulas. Tente novamente mais tarde.");
            response.sendRedirect(request.getContextPath() + "/pages/DashboardAluno.jsp");
        }
    }
}
