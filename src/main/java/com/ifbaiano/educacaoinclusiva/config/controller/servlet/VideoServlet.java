package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
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

	private Connection connection;

	@Override
	public void init() throws ServletException {
		this.connection = DBConfig.criarConexao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{

	VideoAulaDAO dao = new VideoAulaDAO(connection);
	ModuloDAO moduloDAO = new ModuloDAO(connection);

	String action = request.getParameter("action");
	String idStr = request.getParameter("id");

	//recupera o idCurso (pode ser passado como parametro ou armazenado na sessão)
	String idCursoStr = request.getParameter("idCurso");
	int idCurso = 0;

	if(idCursoStr != null){
		idCurso = Integer.parseInt(idCursoStr);
	}
	
	else if(request.getSession().getAttribute("idCurso") != null){
		idCurso = (int) request.getSession().getAttribute("idCurso");
	}

	try{
		List<Modulo> modulos = moduloDAO.listarTodosPorCurso(idCurso);
		request.setAttribute("modulos", modulos);
	}
	
	catch(Exception e){
		e.printStackTrace();
	}

	if("delete".equals(action) && idStr != null){
		//deletando video aula
		try {
			dao.deletarVideoaula(Integer.parseInt(idStr));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/pages/listarvideoAulaTutor.jsp");
		return;
	}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		VideoAulaDAO dao = new VideoAulaDAO(connection);

		String action = request.getParameter("action");
		String idStr = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String url = request.getParameter("url");
		String idModuloStr = request.getParameter("idModulo");

		if(titulo == null || url == null ||idModuloStr == null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros obrigatorios ausentes");
			return;

		}

		int idModulo = Integer.parseInt(idModuloStr);

		if("update".equals(action) && idStr != null){
			//atualizando
			int id = Integer.parseInt(idStr);
			VideoAula video = new VideoAula(id, titulo, url, idModulo);
			try {
				dao.atualizarVideoaula(video);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//criação
			VideoAula video = new VideoAula(0, titulo, url, idModulo);
			try {
				dao.inserirVideoaula(video);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//volatando a lista de video aulas
		response.sendRedirect(request.getContextPath() + "/pages/listarvideoaulaTutor.jsp");

	}
}