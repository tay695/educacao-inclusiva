package com.ifbaiano.educacaoinclusiva.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

@WebServlet("/CadastroTutor")
public class CadastroTutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senhaDigitada = request.getParameter("senha");
			String bio = request.getParameter("bio");
			String areaEspecializacao = request.getParameter("areaEspecializacao");

			  List<String> erros = new ArrayList<>();
		        if (nome == null || nome.isBlank()) erros.add("Nome é obrigatório.");
		        if (email == null || email.isBlank()) erros.add("Email é obrigatório.");
		        if (senhaDigitada == null || senhaDigitada.isBlank()) erros.add("Senha é obrigatória.");
		        if (areaEspecializacao == null || areaEspecializacao.isBlank()) erros.add("Área de especialização é obrigatória.");
		        if (!erros.isEmpty()) {
		            request.setAttribute("erros", erros);
		            request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
		            return;
		        }
		        
			String salt = SenhaUtils.gerarSalt();
			String senha = SenhaUtils.gerarHashSenha(senhaDigitada, salt);

			try (Connection conexao = DBConfig.criarConexao()) {
				Tutor tutor = new Tutor(areaEspecializacao, 0, nome, email, senha, bio);
				tutor.setSalt(salt);

				TutorDAO tutorDAO = new TutorDAO(conexao);
				tutorDAO.adicionarTutor(tutor);
				response.sendRedirect("pages/login.jsp");

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("erro", "Erro ao cadastrar tutor.");
				request.getRequestDispatcher("/pages/cadastro.jsp").forward(request, response);
			}
		}
}