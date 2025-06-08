package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/inscricao")
public class InscricaoServlet extends HttpServlet {

   
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String cursoIdStr = request.getParameter("idCurso");
        if (cursoIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Curso não especificado.");
            return;
        }

        int idCurso;
        try {
            idCurso = Integer.parseInt(cursoIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do curso inválido.");
            return;
        }

        SessionDTO sessionDTO = (SessionDTO) request.getSession().getAttribute("usuarioLogado");
        if (sessionDTO == null) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        Usuario usuario = sessionDTO.getUsuario();
        if (usuario == null) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

		try (Connection conexao = DBConfig.criarConexao()){
            String sql = "INSERT INTO UsuarioCurso (data_inscricao, estado, nota_final, id_usuario, id_curso) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt =conexao.prepareStatement(sql)) {
                stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                stmt.setString(2, "ativo");
                stmt.setNull(3, java.sql.Types.DECIMAL); // nota_final começa como null
                stmt.setInt(4, usuario.getId());         // ID do usuário logado
                stmt.setInt(5, idCurso);                 // ID do curso

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            // Aqui você pode verificar erro de chave duplicada (usuário já inscrito)
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao realizar inscrição: " + e.getMessage());
            request.getRequestDispatcher("/pages/cursos.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/pages/cursos.jsp");
    }
}
