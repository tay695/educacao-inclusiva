package com.ifbaiano.educacaoinclusiva.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/perfil")
public class PerfilUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AlunoDAO alunoDAO;
    private TutorDAO tutorDAO;


@Override
public void init() throws ServletException {
    super.init();
    try {
        Connection conexao = DBConfig.criarConexao();
        alunoDAO = new AlunoDAO(conexao);
        tutorDAO = new TutorDAO(conexao);
    } catch (Exception e) {
        throw new ServletException("Erro ao iniciar DAOs no PerfilUsuarioServlet", e);
    }
}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("/pages/login/login.jsp");
            return;
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        try {
            Aluno aluno = alunoDAO.buscarAlunoPorEmail(usuarioLogado.getEmail());
            if (aluno != null) {
                request.setAttribute("usuario", aluno);
                request.getRequestDispatcher("/pages/perfil/aluno.jsp").forward(request, response);
                return;
            }

            Tutor tutor = tutorDAO.buscarTutorPorEmail(usuarioLogado.getEmail());
            if (tutor != null) {
                request.setAttribute("usuario", tutor);
                request.getRequestDispatcher("/pages/perfil/tutor.jsp").forward(request, response);
                return;
            }


        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar perfil do usuário", e);
        }
    }
}
