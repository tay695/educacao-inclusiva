package com.ifbaiano.educacaoinclusiva.config.controller.servlet;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/inscricao")
public class InscricaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        // Pega o curso escolhido pelo id enviado no formulário - Lembrando q a ideia é de que tenha uma lista de cursos, e o usuario só precisa clicar para se inscrever.
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

	HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
	    
        if (usuario == null) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        try (Connection con = DBConfig.criarConexao()) {
            String sql = "INSERT INTO UsuarioCurso (data_inscricao, estado, nota_final, id_usuario, id_curso) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                stmt.setString(2, "ativo");
                stmt.setNull(3, java.sql.Types.DECIMAL); // nota_final começa null
				
                stmt.setInt(5, idCurso);

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            // Pode tratar duplicidade aqui (aluno já inscrito)
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao realizar inscrição: " + e.getMessage());
            request.getRequestDispatcher("cursos.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("pages/cursos.jsp");
    }
}
