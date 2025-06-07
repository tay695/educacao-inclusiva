package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.enums.TipoDeUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TutorServlet")
public class TutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senhaDigitada = request.getParameter("senha");
		String bio = request.getParameter("bio");
		String areaEspecializacao = request.getParameter("area_especializacao");

		try (Connection conexao = DBConfig.criarConexao()) {
			
			Tutor tutor = new Tutor(areaEspecializacao,0, nome, email, senhaDigitada, bio, TipoDeUsuario.tutor.name());

			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			int idGerado = usuarioDAO.inserir(tutor); 

			if (idGerado > 0) {
				tutor.setId(idGerado); 
				TutorDAO tutorDAO = new TutorDAO(conexao);
				tutorDAO.inserirTutor(tutor);
				System.out.println("Tutor cadastrado com sucesso: " + email);
				response.sendRedirect("pages/login.jsp");
			} else {
				throw new SQLException("Falha ao inserir usuário base.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar tutor: " + e.getMessage());
			request.getRequestDispatcher("pages/cadastroTutor.jsp").forward(request, response);
		}
	}
}

