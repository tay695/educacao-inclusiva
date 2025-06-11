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

@WebServlet("/modulovideo")
public class ModuloVideoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Tutor tutor = (Tutor) session.getAttribute("tutor");

        if (tutor == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if (!"inserir".equals(action)) {
            session.setAttribute("erro", "Ação inválida!");
            response.sendRedirect(request.getContextPath() + "/pages/cadastrosaVideos.jsp");
            return;
        }

        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
       
            if (titulo == null || titulo.trim().isEmpty() || 
            descricao == null || descricao.trim().isEmpty()) {
            session.setAttribute("erro", "Preencha os dados do módulo!");
            response.sendRedirect(request.getContextPath() + "/pages/cadastroVideos.jsp");
            return;
        }
            String tituloV = request.getParameter("titulo");
            String url = request.getParameter("url");
            
            if (tituloV.trim().isEmpty() || url.trim().isEmpty()) {
                session.setAttribute("erro", "Preencha todos os dados da videoaula!");
                response.sendRedirect(request.getContextPath() + "/pages/cadastroVideos.jsp");
                return;
            }

            if (!url.trim().startsWith("http")) {
                session.setAttribute("erro", "URL do vídeo inválida!");
                response.sendRedirect(request.getContextPath() + "/pages/cadastroVideos.jsp");
                return;
            }
	

        try (Connection conexao = DBConfig.criarConexao()) {
            ModuloDAO moduloDAO = new ModuloDAO(conexao);

            Modulo modulo = new Modulo();
            modulo.setTitulo(titulo.trim());
            modulo.setDescricao(descricao.trim());
            moduloDAO.inserirModulo(modulo); 
            

            VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conexao);
            VideoAula video = new VideoAula();
           
                video.setTitulo(tituloV.trim());
                video.setUrl(url.trim());
                video.setmoduloId(modulo.getId());
                System.out.println("Inserindo " + video + " vídeos...");
                videoAulaDAO.inserirVideoaula(video);
                session.setAttribute("sucesso", "Cadastro realizado com sucesso!");
                response.sendRedirect(request.getContextPath() + "/pages/dashboardTutor.jsp");
                return; 
                
            } catch (SQLException e) {
                session.setAttribute("erro", "Erro: " + e.getMessage());
                response.sendRedirect(request.getContextPath() + "/pages/cadastroVideos.jsp");
                return; 
            }
        }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/pages/cadastroVideos.jsp").forward(request, response);
	}
}
