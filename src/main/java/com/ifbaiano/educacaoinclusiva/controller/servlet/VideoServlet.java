package com.ifbaiano.educacaoinclusiva.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/videoaula")
public class VideoServlet extends HttpServlet {

	private Connection connection;

	@Override
	public void init() throws ServletException {
		this.connection = DBConfig.criarConexao();
	}

	@Override // tratando requisições http post(normalmente usada para salvar dados)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titulo = request.getParameter("titulo");
		String url = request.getParameter("url");
		String idModuloStr = request.getParameter("idModulo");
		if (titulo == null || url == null || idModuloStr == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametros obrigatórios ausentes!");
			return;
		}

		try {
			int idModulo = Integer.parseInt(idModuloStr);
			VideoAula videoaula = new VideoAula(0, titulo, url, idModulo);
			VideoAulaDAO dao = new VideoAulaDAO(connection);
			dao.inserirVideoaula(videoaula);

			response.sendRedirect("sucesso.jsp");

		}

		catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao salvar vídeo");
		}
	}

	@Override //
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idModuloStr = request.getParameter("idModulo"); //
		if (idModuloStr == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id do modulo é obrigatório");
			return;
		}

		try {
			int idModulo = Integer.parseInt(idModuloStr);

			VideoAulaDAO dao = new VideoAulaDAO(connection);// usando o DAO para buscar as video aula do modulo
			List<VideoAula> videoaulas = dao.listarVideoaula(idModulo);
			request.setAttribute("videoaulas", videoaulas); // envia a lista de video aulas, como atributo, para o jsp
			request.getRequestDispatcher("/pages/listarVideos.jsp").forward(request, response); // encaminha a
																								// requisiçaõ para a
																								// página jsp que vai
																								// exibir os vídeos
		}

		catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar vídeos");

		}
	}

}