package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

@WebServlet("/cadastroTutor")
public class CadastroTutorServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.getWriter().append("Olá mundo").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroTutor.jsp");
		dispatcher.forward(request, response);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String areaEspecializacao = request.getParameter("area_especializacao");
    	String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senhaDigitada = request.getParameter("senha");
        String bio = request.getParameter("bio");

        try (Connection conexao = DBConfig.criarConexao()) {
         Usuario usuario = new Usuario(nome, email, senhaDigitada, bio, TipoDeUsuario.tutor.toString());

            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            int idUsuario = usuarioDAO.inserir(usuario);

            if (idUsuario == -1) {
                throw new SQLException("Falha ao inserir usuário.");
            }

            Tutor tutor = new Tutor(areaEspecializacao, idUsuario, nome, email,senhaDigitada, bio, TipoDeUsuario.tutor.toString());

            TutorDAO tutorDAO = new TutorDAO(conexao);
            tutorDAO.inserirTutor(tutor); 
            System.out.println("ID atribuído ao tutor: " + tutor.getId());

            System.out.println("Cadastro realizado para: " + email);
            response.sendRedirect("pages/login.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar tutor.");
            request.getRequestDispatcher("pages/cadastroTutor.jsp").forward(request, response);
        }
    }
}
