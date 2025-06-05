package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

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
import com.ifbaiano.educacaoinclusiva.utils.SenhaUtils;

@WebServlet("/cadastroTutor")
public class CadastroTutorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senhaDigitada = request.getParameter("senha");
        String bio = request.getParameter("bio");
        String areaEspecializacao = request.getParameter("area_especializacao");

        try (Connection conexao = DBConfig.criarConexao()) {

            String salt = SenhaUtils.gerarSalt();
            String senhaHasheada = SenhaUtils.gerarHashComSalt(senhaDigitada, salt);  

            Usuario usuario = new Usuario(0, nome, email, senhaHasheada, bio);
            usuario.setSalt(salt);
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            
            System.out.println("Chamando UsuarioDAO.inserir...");
            int idUsuario = usuarioDAO.inserir(usuario); 
            System.out.println("ID retornado do insert: " + idUsuario);

            if (idUsuario == -1) {
                throw new SQLException("Falha ao inserir usuário.");
            }

            Tutor tutor = new Tutor(areaEspecializacao, idUsuario, nome, email, senhaHasheada, bio);
            tutor.setSalt(salt);
            TutorDAO tutorDAO = new TutorDAO(conexao);
            tutorDAO.adicionarTutor(tutor);

            System.out.println("Cadastro realizado para: " + email);
            response.sendRedirect("pages/login.jsp");
        

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar tutor.");
            request.getRequestDispatcher("pages/cadastroTutor.jsp").forward(request, response);
        }
    }
}
