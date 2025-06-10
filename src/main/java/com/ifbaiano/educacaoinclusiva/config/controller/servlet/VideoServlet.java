package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/videoaula")
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	@Override
	public void init() throws ServletException {
		this.connection = DBConfig.criarConexao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String idStr = request.getParameter("id");
		String moduloId = request.getParameter("moduloId");

		HttpSession session = request.getSession();
		Tutor tutor = (Tutor) session.getAttribute("tutor");

		if (tutor == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try (Connection conexao = DBConfig.criarConexao()) {
			VideoAulaDAO videoDAO = new VideoAulaDAO(conexao);
			ModuloDAO moduloDAO = new ModuloDAO(conexao);

			int idModulo = Integer.parseInt(request.getParameter("idModulo"));

			VideoAula video = new VideoAula();
			video.setTitulo(request.getParameter("titulo"));
			video.setUrl(request.getParameter("url"));
			video.setmoduloId(idModulo);

			VideoAulaDAO videoAulaDAO = new VideoAulaDAO(null);
			videoAulaDAO.inserirVideoaula(video);

			// Deletar vídeo
			if ("delete".equals(action) && idStr != null) {
				int id = Integer.parseInt(idStr);
				videoDAO.deletarVideoaula(id);
				session.setAttribute("mensagem", "Videoaula deletada com sucesso!");
				response.sendRedirect(request.getContextPath() + "/videoaula");
				return;
			}

			if ("novo".equals(action) && moduloId != null) {
				request.setAttribute("idModuloSelecionado", Integer.parseInt(moduloId));
			}

			List<Modulo> modulos = moduloDAO.listarModulosComVideos();
			request.setAttribute("modulos", modulos);

			request.getRequestDispatcher("/pages/dashboardTutor.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Tutor tutor = (Tutor) session.getAttribute("tutor");
		if (tutor == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String action = request.getParameter("action");
		String titulo = request.getParameter("titulo");
		String url = request.getParameter("url");
		String idModuloStr = request.getParameter("idModulo");

		if (titulo == null || titulo.trim().isEmpty() || url == null || url.trim().isEmpty() || !url.startsWith("http")
				|| idModuloStr == null || idModuloStr.trim().isEmpty()) {

			session.setAttribute("erro", "Preencha todos os campos corretamente");
			response.sendRedirect(request.getContextPath() + "/pages/cadastroVideoAula.jsp");
			return;
		}

		try (Connection conexao = DBConfig.criarConexao()) {
			VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conexao);
			int idModulo = Integer.parseInt(idModuloStr);

			switch (action) {
			case "inserir":
				VideoAula novaAula = new VideoAula();
				novaAula.setTitulo(titulo.trim());
				novaAula.setUrl(url.trim());
				novaAula.setmoduloId(idModulo);

				videoAulaDAO.inserirVideoaula(novaAula);
				session.setAttribute("sucesso", "Videoaula cadastrada com sucesso!");
				response.sendRedirect(request.getContextPath() + "/pages/cadastroVideoAular.jsp?idModulo=" + idModulo);

				break;

			default:
				session.setAttribute("erro", "Ação inválida!");
				response.sendRedirect(request.getContextPath() + "/pages/dashboardTutor.jsp");
			}

			VideoAula novaAula = new VideoAula();
			novaAula.setTitulo(titulo.trim());
			novaAula.setUrl(url.trim());
			novaAula.setmoduloId(idModulo);

			videoAulaDAO.inserirVideoaula(novaAula);

			Modulo moduloAtualizado;
			ModuloDAO moduloDAO = new ModuloDAO(connection);
			Modulo modulo = moduloDAO.buscarModuloCompleto(idModulo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conexao = DBConfig.criarConexao()) {
			VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conexao);
			ModuloDAO moduloDAO = new ModuloDAO(conexao);

			int idModulo = Integer.parseInt(idModuloStr);

			VideoAula novaAula = new VideoAula();
			novaAula.setTitulo(titulo.trim());
			novaAula.setUrl(url.trim());
			novaAula.setmoduloId(idModulo);

			videoAulaDAO.inserirVideoaula(novaAula);

			List<Modulo> modulos = moduloDAO.listarModulosComVideos();
			Modulo moduloAtualizado = moduloDAO.buscarModuloCompleto(idModulo);

			request.setAttribute("modulos", modulos);
			request.setAttribute("modulo", moduloAtualizado);
			session.setAttribute("sucesso", "Videoaula cadastrada com sucesso!");

			request.getRequestDispatcher("/pages/dashboardTutor.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			session.setAttribute("erro", "ID do módulo inválido!");
			response.sendRedirect(request.getContextPath() + "/pages/cadastroVideoAula.jsp");
		} catch (SQLException e) {
			session.setAttribute("erro", "Erro no banco de dados: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/pages/cadastroVideoAula.jsp");
		} catch (Exception e) {
			session.setAttribute("erro", "Erro inesperado: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/pages/dashboardTutor.jsp");
		}
	}
}