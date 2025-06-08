package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AtualizarPerfilTutorServlet")
public class AtualizarPerfilTutorServlet extends HttpServlet {
	private Connection conexao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("tutor") == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Tutor tutorSessao = (Tutor) session.getAttribute("tutor");

        String campo = request.getParameter("campo");
        String valor = request.getParameter("valor");
        int id = Integer.parseInt(request.getParameter("id"));

        if(id != tutorSessao.getId()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Validação básica dos campos
        if(!List.of("nome", "email", "bio", "area").contains(campo)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

    	try (Connection conexao = DBConfig.criarConexao()) {
            TutorDAO dao = new TutorDAO(conexao);

            switch(campo) {
                case "nome":
                    tutorSessao.setNome(valor);
                    break;
                case "email":
                    tutorSessao.setEmail(valor);
                    break;
                case "bio":
                    tutorSessao.setBio(valor);
                    break;
                case "area":
                    tutorSessao.setAreaEspecializacao(valor);
                    break;
            }

            dao.atualizarTutor(tutorSessao);
            session.setAttribute("tutor", tutorSessao); // Atualiza sessão

            response.setStatus(HttpServletResponse.SC_OK);
        } catch(SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
