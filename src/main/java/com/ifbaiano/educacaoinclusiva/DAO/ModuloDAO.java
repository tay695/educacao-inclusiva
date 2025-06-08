package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class ModuloDAO {
    private Connection conexao;

    public ModuloDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirModulo(Modulo modulo, int idCurso) throws SQLException {
        String sql = "INSERT INTO Modulo (titulo, descricao, id_curso) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, modulo.getTitulo());
            stmt.setString(2, modulo.getDescricao());
            stmt.setInt(3, idCurso);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Falha ao inserir o módulo.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    modulo.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Modulo> listarTodosPorCurso(int idCurso) throws SQLException {
        List<Modulo> modulos = new ArrayList<>();
        String sql = "SELECT * FROM Modulo WHERE id_curso = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idModulo = rs.getInt("id");
                    List<VideoAula> videoaulas = listarVideoaulasModulo(idModulo);
                    Modulo modulo = new Modulo(
                        idModulo,
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        videoaulas
                    );
                    modulos.add(modulo);
                }
            }
        }
        return modulos;
    }

    public void atualizarModulo(Modulo modulo) throws SQLException {
        String sql = "UPDATE Modulo SET titulo = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, modulo.getTitulo());
            stmt.setString(2, modulo.getDescricao());
            stmt.setInt(3, modulo.getId());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Houve uma falha na inserção do novo módulo, tente novamente.");
            }
        }
    }

    public void deletarModulo(int id) throws SQLException {
        String sql = "DELETE FROM Modulo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Falha ao deletar o módulo.");
            }
        }
    }

    public Modulo buscarModulo(int id) throws SQLException {
        String sql = "SELECT * FROM Modulo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    List<VideoAula> videoaulas = listarVideoaulasModulo(id);
                    return new Modulo(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        videoaulas
                    );
                }
            }
        }
        return null;
    }

    private List<VideoAula> listarVideoaulasModulo(int idModulo) throws SQLException {
        List<VideoAula> videoaulas = new ArrayList<>();
        String sql = "SELECT * FROM Videoaula WHERE id_modulo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idModulo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VideoAula videoaula = new VideoAula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("url"),
                        rs.getInt("id_modulo")
                    );
                    videoaulas.add(videoaula);
                }
            }
        }
        return videoaulas;
    }
}
