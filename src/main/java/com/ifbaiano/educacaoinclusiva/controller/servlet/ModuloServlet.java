package com.ifbaiano.educacaoinclusiva.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.model.Modulo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modulo")
public class ModuloServlet extends HttpServlet {
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String idCursoStr = request.getParameter("idCurso");
        String idModuloStr = request.getParameter("idModulo");

        int idCurso = 0;
        int idModulo = 0;

        try {
            if (idCursoStr != null && !idCursoStr.isEmpty()) {
                idCurso = Integer.parseInt(idCursoStr);
            }
            if (idModuloStr != null && !idModuloStr.isEmpty()) {
                idModulo = Integer.parseInt(idModuloStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "ID inválido.");
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seubanco", "usuario", "senha")) {
            ModuloDAO moduloDAO = new ModuloDAO(connection);

            if ("inserir".equalsIgnoreCase(acao)) {
                Modulo modulo = new Modulo();
                modulo.setTitulo(titulo);
                modulo.setDescricao(descricao);

                moduloDAO.inserirModulo(modulo, idCurso);
                response.sendRedirect("moduloListar?idCurso=" + idCurso);

            } else if ("atualizar".equalsIgnoreCase(acao)) {
                Modulo modulo = new Modulo();
                modulo.setId(idModulo);
                modulo.setTitulo(titulo);
                modulo.setDescricao(descricao);

                moduloDAO.atualizarModulo(modulo);
                response.sendRedirect("moduloListar?idCurso=" + idCurso);

            } else if ("deletar".equalsIgnoreCase(acao)) {
                moduloDAO.deletarModulo(idModulo);
                response.sendRedirect("moduloListar?idCurso=" + idCurso);

            } else {
                request.setAttribute("erro", "Ação inválida.");
                request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro no banco de dados: " + e.getMessage());
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idCursoStr = request.getParameter("idCurso");
        int idCurso = 0;

        try {
            if (idCursoStr != null && !idCursoStr.isEmpty()) {
                idCurso = Integer.parseInt(idCursoStr);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "ID do curso inválido.");
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Capacita", "usuario", "senha")) {
            ModuloDAO moduloDAO = new ModuloDAO(connection);
            List<Modulo> modulos = moduloDAO.listarTodosPorCurso(idCurso);

            request.setAttribute("modulos", modulos);
            request.setAttribute("idCurso", idCurso);
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao listar módulos: " + e.getMessage());
            request.getRequestDispatcher("/pages/modulo.jsp").forward(request, response);
        }
    }
}
