package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;
@WebServlet("/pages/homeTutor.jsp")
public class HomeTutorServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionDTO sessionDTO = (SessionDTO) request.getSession().getAttribute("usuarioLogado");

        if (sessionDTO == null) {
            response.sendRedirect("login.jsp");
            return;
        }

      //*  try {
        //*    VideoAulaDAO dao = new VideoAulaDAO();
         //*   List<VideoAula> lista = dao.listarPorTutor(sessionDTO.getId());
          //*  request.setAttribute("listaVideoaulas", lista);
         //*   request.getRequestDispatcher("/pages/homeTutor.jsp").forward(request, response);
        //*} catch (Exception e) {
        //*    e.printStackTrace();
        //*    response.sendError(500, "Erro ao carregar videoaulas");
	}
}
