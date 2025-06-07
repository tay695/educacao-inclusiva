package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        SessionDTO sessionDTO = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (sessionDTO == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String email = sessionDTO.getEmail();
        TipoDeUsuario tipo = sessionDTO.getTipo();

        try {
            if (tipo == TipoDeUsuario.aluno) {
                Aluno aluno = alunoDAO.buscarEmail(email);
                if (aluno != null) {
                    request.setAttribute("usuario", aluno);
                    request.getRequestDispatcher("/pages/perfil/aluno.jsp").forward(request, response);
                    return;
                }
            }

            if (tipo == TipoDeUsuario.tutor) {
                Tutor tutor = tutorDAO.buscarTutorPorEmail(email);
                if (tutor != null) {
                    request.setAttribute("usuario", tutor);
                    request.getRequestDispatcher("/pages/perfil/tutor.jsp").forward(request, response);
                    return;
                }
            }

            response.sendRedirect("login.jsp");

        } catch (SQLException e) {
            throw new ServletException("Erro ao buscar perfil do usuário", e);
        }
    }
}
