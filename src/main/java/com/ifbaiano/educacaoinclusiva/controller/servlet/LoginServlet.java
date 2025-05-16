package com.ifbaiano.educacaoinclusiva.controller.servlet;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.controller.LoginController;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginController loginController;

    @Override
    public void init() throws ServletException {
        super.init();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        loginController = new LoginController(usuarioDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            List<ErroCampo> erros = loginController.autenticar(email, senha);

            if (!erros.isEmpty()) {
                request.setAttribute("erro", "Email ou senha inválidos");
                request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
                return;
            }

            // Simulação de login bem-sucedido
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", email);
            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            throw new ServletException("Erro ao autenticar", e);
        }
    }
}
