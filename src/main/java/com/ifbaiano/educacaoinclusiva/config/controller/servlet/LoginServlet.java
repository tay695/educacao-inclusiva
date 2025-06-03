package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.controller.LoginController;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.dto.LoginDTO;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginController loginController;

	@Override
	public void init() throws ServletException {
		super.init();
		UsuarioDAO usuarioDAO = new UsuarioDAO(DBConfig.criarConexao());
		loginController = new LoginController(usuarioDAO);
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

			if (!erros.isEmpty()) {
				request.setAttribute("erros", erros);
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				return;
			}

			Usuario usuarioLogado = loginController.getUsuarioAutenticado();

			if (usuarioLogado == null) {
				request.setAttribute("erro", "Usuário não encontrado.");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuarioLogado);
			if (usuarioLogado instanceof Tutor) {
				response.sendRedirect(request.getContextPath() + "/pages/HomeTutor.jsp");
			} else if (usuarioLogado instanceof Aluno) {
				response.sendRedirect(request.getContextPath() + "/pages/homeAluno.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/login?erro=1");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/login?erro=1");
		}
	}
}