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

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;

@WebServlet("/cadastroAluno")
public class CadastroAlunoServlet extends HttpServlet {
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Olá mundo").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cadastroAluno.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nome = request.getParameter("nome");
	    String email = request.getParameter("email");
	    String senhaDigitada = request.getParameter("senha");
	    String bio = request.getParameter("bio");

		try (Connection conexao = DBConfig.criarConexao()){

	        Usuario usuario = new Usuario(0, nome, email, senhaDigitada, bio, TipoDeUsuario.tutor.toString());
	        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
	        int idUsuario = usuarioDAO.inserir(usuario);
	        
	        if (idUsuario == -1) {
	            throw new SQLException("Falha ao inserir usuário.");
	        }

	        Aluno novoAluno = new Aluno(idUsuario, nome, email, senhaDigitada, bio, TipoDeUsuario.aluno.toString());
	        AlunoDAO alunoDAO = new AlunoDAO(conexao);
	        alunoDAO.inserirAluno(novoAluno);

	        System.out.println("Cadastro realizado para: " + email);
	        response.sendRedirect("pages/login.jsp");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Erro ao cadastrar aluno. Tente novamente.");
	        request.getRequestDispatcher("/pages/cadastroAluno.jsp").forward(request, response);
	    }
	}
}
