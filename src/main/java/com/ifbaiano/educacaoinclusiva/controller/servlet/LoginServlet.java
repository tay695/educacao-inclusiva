
package com.ifbaiano.educacaoinclusiva.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.controller.LoginController;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
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
		request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		LoginDTO loginDTO = new LoginDTO(email, senha);

		try {

			List<ErroCampo> erros = loginController.autenticar(loginDTO);

			if (!erros.isEmpty()) {
				request.setAttribute("erro, tente novemente ", erros);
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				return;
			}

			Usuario usuario = loginController.getUsuarioautenticado();

			if (usuario == null) {
				request.setAttribute("erro", "Usuário não encontrado.");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuario);
			if(usuario instanceof Tutor) {
				response.sendRedirect("pages/perfiltutor.jsp");
			}
			
			if (usuario instanceof Aluno) {
				response.sendRedirect("pages/perfilaluno");
			} else {
				System.out.println("Houve um erro");
				request.setAttribute(" error ", " Usuário não identificado");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				
			}
		} catch (SQLException e) {
		    System.out.println("Erro ao realizar login: " + e.getMessage());
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);

		}
	}
}
