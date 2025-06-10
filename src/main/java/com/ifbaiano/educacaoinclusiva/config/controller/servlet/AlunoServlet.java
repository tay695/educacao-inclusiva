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
import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;

@WebServlet("/dashboardAluno")
public class AlunoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conexao;

    private AlunoDAO alunoDAO = new AlunoDAO(conexao);

    @Override
	public void init() throws ServletException {
		this.conexao = DBConfig.criarConexao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
        SessionDTO sessionDTO = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (sessionDTO == null || !(sessionDTO.getUsuario() instanceof Aluno)) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        Aluno aluno = (Aluno) sessionDTO.getUsuario();
        List<VideoAula> videoaula = null;
		try {
			videoaula = alunoDAO.buscarAulasDoAluno(aluno.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
        for (VideoAula aula: videoaula) {
            aluno.inscreverAula(aula);        
        }
       
       
        sessionDTO.setUsuario(aluno);
        request.getSession().setAttribute("usuarioLogado", sessionDTO);

        request.setAttribute("aula", videoaula);
        request.getRequestDispatcher("/pages/dashboardAluno.jsp").forward(request, response);
        
        List<Modulo> modulos = null;
        try {
            ModuloDAO moduloDAO = new ModuloDAO(conexao); 
            modulos = moduloDAO.listarModulosComVideos(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("modulos", modulos);	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
