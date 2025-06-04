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

import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

/**
 * Servlet implementation class homeTutorServlet
 */
@WebServlet("/homeTutor")
public class homeTutorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Tutor tutor = (Tutor) request.getSession().getAttribute("tutor");
        if (tutor == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConfig.criarConexao()) {
            VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conn);
            List<VideoAula> lista = videoAulaDAO.listarPorTutor(tutor.getId());
            request.setAttribute("listaVideoaulas", lista);
            request.getRequestDispatcher("/pages/homeTutor.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}
