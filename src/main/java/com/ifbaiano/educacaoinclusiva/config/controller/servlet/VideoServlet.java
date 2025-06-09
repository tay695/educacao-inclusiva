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
import java.util.List;

@WebServlet("/videoaula")
public class VideoServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        this.connection = DBConfig.criarConexao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if ("novo".equals(request.getParameter("action"))) {
            String moduloId = request.getParameter("moduloId");
            if (moduloId != null) {
                request.setAttribute("idModuloSelecionado", Integer.parseInt(moduloId));
            }
    	HttpSession session = request.getSession();
        Tutor tutor = (Tutor) session.getAttribute("tutor");
        
        if (tutor == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String action = request.getParameter("action");
            String idStr = request.getParameter("id");
            
            VideoAulaDAO videoDAO = new VideoAulaDAO(connection);
            ModuloDAO moduloDAO = new ModuloDAO(connection);

            if ("delete".equals(action) && idStr != null) {
                int id = Integer.parseInt(idStr);
                videoDAO.deletarVideoaula(id);
                session.setAttribute("mensagem", "Videoaula deletada com sucesso!");
                response.sendRedirect(request.getContextPath() + "/videoaula");
                return;
            }

            if ("edit".equals(action) && idStr != null) {
                VideoAula video = videoDAO.buscarVideoaula(Integer.parseInt(idStr));
                request.setAttribute("video", video);
            }

            List<Modulo> modulos = moduloDAO.listarModulos();
            request.setAttribute("modulos", modulos);
            
            if (moduloId != null) {
                request.setAttribute("idModuloSelecionado", Integer.parseInt(moduloId));
            }
            request.getRequestDispatcher("/pages/formularioCadastroVideoAula.jsp").forward(request, response);
            
        } catch (Exception e) {
            throw new ServletException(e);
        }

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
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String url = request.getParameter("url");
        String idModuloStr = request.getParameter("idModulo");

        // Validação
        if (titulo == null || titulo.trim().isEmpty() || 
            url == null || !url.startsWith("http") || 
            idModuloStr == null) {
            
            session.setAttribute("erro", "Dados inválidos fornecidos");
            response.sendRedirect(request.getContextPath() + "/videoaula");
            return;
        }

        try {
            VideoAulaDAO aula = new VideoAulaDAO(connection);
            int idModulo = Integer.parseInt(idModuloStr);
            VideoAula video;

            if ("update".equals(action) && idStr != null) {
                int id = Integer.parseInt(idStr);
                video = new VideoAula(id, titulo, url, idModulo);
               aula.atualizarVideoaula(video);
                session.setAttribute("mensagem", "Vídeo-aula atualizada com sucesso!");
            }
                video = new VideoAula(0, titulo, url, idModulo);
               aula.inserirVideoaula(video);
                session.setAttribute("mensagem", "Vídeo-aula criada com sucesso!");
            response.sendRedirect(request.getContextPath() + "/videoaula");

        } catch (Exception e) {
            session.setAttribute("erro", "Erro ao processar videoaula: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/pages/dashboardTutor");
        }
    }
    

}