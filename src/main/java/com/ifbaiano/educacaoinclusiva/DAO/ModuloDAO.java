package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class ModuloDAO {
    private Connection conexao;

    public ModuloDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirModulo(Modulo modulo) throws SQLException {
        String sql = "INSERT INTO Modulo (titulo, descricao, id_curso) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, modulo.getTitulo());
            stmt.setString(2, modulo.getDescricao());
            stmt.setInt(3, modulo.getIdCurso());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    modulo.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<Modulo> listarPorCurso(int idCurso) throws SQLException {
        List<Modulo> modulos = new ArrayList<>();
        String sql = "SELECT * FROM Modulo WHERE id_curso = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Modulo modulo = new Modulo();
                    modulo.setId(rs.getInt("id"));
                    modulo.setTitulo(rs.getString("titulo"));
                    modulo.setDescricao(rs.getString("descricao"));
                    modulo.setIdCurso(rs.getInt("id_curso")); 
                    modulos.add(modulo);
                }
            }
        }
        return modulos;
    }

    public void atualizarModulo(Modulo modulo) throws SQLException {
        String sql = "UPDATE Modulo SET titulo = ?, descricao = ?, id_curso = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, modulo.getTitulo());
            stmt.setString(2, modulo.getDescricao());
            stmt.setInt(3, modulo.getIdCurso());
            stmt.setInt(4, modulo.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum módulo encontrado com o ID: " + modulo.getId());
            }
        }
    }

    public Modulo buscarModuloCompleto(int idModulo) throws SQLException {
        String sql = "SELECT id, titulo, descricao, id_curso FROM Modulo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idModulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Modulo modulo = new Modulo();
                    modulo.setId(rs.getInt("id"));
                    modulo.setTitulo(rs.getString("titulo"));
                    modulo.setDescricao(rs.getString("descricao"));
                    modulo.setIdCurso(rs.getInt("id_curso")); 
                    return modulo;
                }
            }
        }
        return null;
    }

    public void deletarModulo(int idModulo) throws SQLException {
        String sqlDeleteVideos = "DELETE FROM Videoaula WHERE id_modulo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlDeleteVideos)) {
            stmt.setInt(1, idModulo);
            stmt.executeUpdate();
        }

        String sqlDeleteModulo = "DELETE FROM Modulo WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sqlDeleteModulo)) {
            stmt.setInt(1, idModulo);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Módulo não encontrado ou já deletado.");
            }
        }
    }

    private List<VideoAula> listarVideoaulasModulo(int idModulo) throws SQLException {
        List<VideoAula> videoaulas = new ArrayList<>();
        String sql = "SELECT id, titulo, url, id_modulo FROM VideoAula WHERE id_modulo = ?";
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

    public List<Modulo> listarModulosComVideos() {
        List<Modulo> modulos = new ArrayList<>();
        String sql = "SELECT id, titulo, descricao, id_curso FROM Modulo";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Modulo modulo = new Modulo();
                int idModulo = rs.getInt("id");
                modulo.setId(idModulo);
                modulo.setTitulo(rs.getString("titulo"));
                modulo.setDescricao(rs.getString("descricao"));
                modulo.setIdCurso(rs.getInt("id_curso"));
                modulo.setVideoAulas(listarVideoaulasModulo(idModulo));

                modulos.add(modulo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar módulos com vídeos: " + e.getMessage());
            e.printStackTrace();
        }

        return modulos;
    }
}
