
package com.ifbaiano.educacaoinclusiva.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.controller.LoginController;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.dto.LoginDTO;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	// apenas uma boa prática , mecanismo de serialização do java, o eclipse ficando dando um alerta ai adicionei a sugestão
	private static final long serialVersionUID = 1L;
	private LoginController loginController;

	@Override
	public void init() throws ServletException {
		super.init();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		loginController = new LoginController(usuarioDAO);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Encaminha o usuário para a página de login
		request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
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
				request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
				return;
			}
			// salvando o usuário depois da autenticação e redirecionando-o para a página
			// principal do site
			Usuario usuario = loginController.getUsuarioautenticado();
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuario);
			response.sendRedirect("index.jsp");

		} catch (SQLException e) {
			throw new ServletException("Houve um erro de login ", e);
		}
	}
}
