package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.controller.LoginController;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.dto.LoginDTO;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private LoginController loginController;
	private Connection conexao;

	@Override
	public void init() throws ServletException {
		super.init();
		conexao = DBConfig.criarConexao();
		loginController = new LoginController(new UsuarioDAO(conexao));
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		LoginDTO loginDTO = new LoginDTO(email, senha);

		try {
			List<ErroCampo> erros = loginController.autenticar(loginDTO);
			Usuario usuarioLogado = loginController.getUsuarioAutenticado();

			if (!erros.isEmpty() || usuarioLogado == null) {
				request.setAttribute("erro", "E-mail ou senha inválidos.");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", new SessionDTO(usuarioLogado));
			System.out.println("Sessão criada para: " + usuarioLogado.getEmail() + ", Sessão ID: " + session.getId());

			if (usuarioLogado instanceof Tutor) {
			    session.setAttribute("tutor", usuarioLogado);
			    response.sendRedirect(request.getContextPath() + "/pages/dashboardTutor.jsp");
			    return;
			} else if (usuarioLogado instanceof Aluno) {
			    session.setAttribute("aluno", usuarioLogado); 
			    response.sendRedirect(request.getContextPath() + "/pages/dashboardAluno.jsp");
			    return;
			}


		} catch (SQLException e) {
			e.printStackTrace(); 
			request.setAttribute("erro", "Erro interno ao tentar realizar login.");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
	}
}
