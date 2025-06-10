package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Modulo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/modulo")
public class ModuloServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    
	    String action = request.getParameter("action");
	    HttpSession session = request.getSession();
	    String idStr = request.getParameter("id");
	    String titulo = request.getParameter("titulo");
	    String descricao = request.getParameter("descricao");

	 

	    try (Connection conexao = DBConfig.criarConexao()) {
	        ModuloDAO moduloDAO = new ModuloDAO(conexao);

	        switch (action != null ? action : "") {
	            case "inserir":
	                Modulo novoModulo = new Modulo();
	                novoModulo.setTitulo(titulo);
	                novoModulo.setDescricao(descricao);
	                
	                moduloDAO.inserirModulo(novoModulo);
	                session.setAttribute("sucesso", "Módulo criado com sucesso!");
	                
	                if ("videoaula".equals(request.getParameter("redirect"))) {
	                	response.sendRedirect(request.getContextPath() + "/pages/formularioCadastroVideoAula.jsp?idModulo=" + novoModulo.getId());
	                }
	                  break;     
	            
	                
	            default:
	                session.setAttribute("erro", "Ação inválida!");
	                response.sendRedirect(request.getContextPath() + "/dashboardTutor.jsp");
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.setAttribute("erro", "Erro: " + e.getMessage());
	       	    }
	}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");

        if ("novo".equals(action)) {
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);
            return;
        }

        try (Connection conexao = DBConfig.criarConexao()) {
            ModuloDAO moduloDAO = new ModuloDAO(conexao);
            
            List<Modulo> modulos = moduloDAO.listarModulosComVideos();
            request.setAttribute("modulos", modulos);
            
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("erro", "Erro: " + e.getMessage());
        }
    }
}