package com.ifbaiano.educacaoinclusiva.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Curso;

public class CursoDAO {

	public void addCurso(Curso curso) {
		String sql = "INSERT INTO Curso (titulo, descricao, area) VALUES (?, ?, ?)";

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = DBConfig.criarConexao();
			pstm = (PreparedStatement) con.prepareStatement(sql);
			pstm.setString(1, curso.getTitulo());
			pstm.setString(2, curso.getDescricao());
			pstm.setString(3, curso.getArea());

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Curso> getCursso() {
		String sql = "Select * from Curso";

		List<Curso> cursos = new ArrayList<Curso>();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			con = DBConfig.criarConexao();
			pstm = con.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Curso curso = new Curso();
				curso.setId(rset.getInt("id"));
				curso.setTitulo(rset.getString("titulo"));
				curso.setDescricao(rset.getString("descricao"));
				curso.setArea(rset.getString("area"));

				cursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cursos;
	}

	public void update(Curso curso) {
		String sql = "UPDATE Curso set titulo = ?, descricao = ?, area = ? WHERE id = ?";

		Connection con = null;
		PreparedStatement pstm = null;
		try {

			con = DBConfig.criarConexao();
			pstm = (PreparedStatement) con.prepareStatement(sql);

			pstm.setString(1, curso.getTitulo());
			pstm.setString(2, curso.getDescricao());
			pstm.setString(3, curso.getArea());
			pstm.setInt(4, curso.getId());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Curso curso) {
		String sql = "DELETE FROM Curso WHERE id = ?";

		Connection con = null;
		PreparedStatement pstm = null;

		try {

			con = DBConfig.criarConexao();
			pstm = (PreparedStatement) con.prepareStatement(sql);

			pstm.setInt(1, curso.getId());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<Curso> pesquisar(String titulo) {
		String sql = "Select * from Curso WHERE titulo = ?";

		List<Curso> cursos = new ArrayList<Curso>();

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			con = DBConfig.criarConexao();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, titulo);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Curso curso = new Curso();
				curso.setId(rset.getInt("id"));
				curso.setTitulo(rset.getString("titulo"));
				curso.setDescricao(rset.getString("descricao"));
				curso.setArea(rset.getString("area"));

				cursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cursos;
	}

}