package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Curso;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.Postagem;


public class PostagemDAO {
	private Connection conexao;

	public PostagemDAO(Connection conexao){
		this.conexao = conexao;
	}
	public void inserirPostagem(Postagem postagem) throws SQLException{
		String sql = "INSERT INTO Postagem (titulo, autor, conteudo, dataHora, curso_id, modulo_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
			stmt.setString(1, postagem.getTitulo());
			stmt.setString(2, postagem.getAutor());
			stmt.setString(3, postagem.getConteudo());
			stmt.setTimestamp(4, Timestamp.valueOf(postagem.getDataHora()));

			if(postagem.getCurso() != null){
				stmt.setInt(5, postagem.getCurso().getId());
			}
			else{
				stmt.setNull(5, Types.INTEGER);
			}

			if(postagem.getModulo() != null){
				stmt.setInt(6, postagem.getModulo().getId());
			}

			else{
				stmt.setNull(6, Types.INTEGER);
			}
			stmt.executeUpdate();
		}
	}
		public List<Postagem> listarTodas() throws SQLException{
		List<Postagem> postagens = new ArrayList<>();
		String sql = "SELECT * FROM Postagem";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String conteudo = rs.getString("conteudo");
				LocalDateTime dataHora = rs.getTimestamp("dataHora").toLocalDateTime();

				Curso curso = null;
				int cursoId = rs.getInt("curso_id");
				if(!rs.wasNull()){
					curso = new Curso(cursoId, null, null, null, null, null);
				}

				Modulo modulo = null;
				int moduloId = rs.getInt("modulo_id");
				if (!rs.wasNull()){
					modulo = new Modulo(moduloId, null, null, null);
				}

				Postagem postagem = new Postagem(titulo, autor, conteudo, id, dataHora, curso, modulo);
				postagens.add(postagem);
			}
		}

		return postagens;
	}

	//busca uma postagem pelo id
	public Postagem buscarPorId(int idBusca) throws SQLException{
		String sql = "SELECT  * FROM Postagem WHERE id = ?";

		try(PreparedStatement stmt = conexao.prepareStatement(sql)){
			stmt.setInt(1, idBusca);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String conteudo = rs.getString("conteudo");
				LocalDateTime dataHora = rs.getTimestamp("dataHora").toLocalDateTime();
						
				Curso curso = null;
				int cursoId = rs.getInt("curso_id");
				if(!rs.wasNull()){
					curso = new Curso(cursoId, null, null, null, null, null);
				}

				Modulo modulo = null;
				int moduloId = rs.getInt("modulo_id");
				if(!rs.wasNull()){
					modulo = new Modulo(moduloId, null, null, null);
				}

						
				return new Postagem(titulo, autor, conteudo, id, dataHora, curso, modulo);
				
			}
		}
			return null;
	}		

	//listando as postagens por modulo
	public List<Postagem> listarPorModulo(int idModulo) throws SQLException{
		List<Postagem> postagens = new ArrayList<>();
		String sql = "SELECT * FROM Postagem WHERE modulo_id = ?";

		try(PreparedStatement stmt = conexao.prepareStatement(sql)){
			stmt.setInt(1, idModulo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String conteudo = rs.getString("conteudo");
				LocalDateTime dataHora = rs.getTimestamp("dataHora").toLocalDateTime();

				Modulo modulo = new Modulo(idModulo, null, null, null);

				Curso curso = null;
				int cursoId = rs.getInt("curso_id");
				if(!rs.wasNull()){
					curso = new Curso(cursoId, null, null, null, null, null);
				}

				Postagem postagem = new Postagem(titulo, autor, conteudo, id, dataHora, curso, modulo);
				postagens.add(postagem);

			}
		}
		return postagens;
		
	}
	//listando postagens por curso
	public List<Postagem> listarPorCurso(int idCurso) throws SQLException{
		List<Postagem> postagens = new ArrayList<>();
		String sql = "SELECT * FROM Postagem WHERE curso_id = ?";

		try(PreparedStatement stmt = conexao.prepareStatement(sql)){
			stmt.setInt(1, idCurso);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id  = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				String conteudo = rs.getString("conteudo");
				LocalDateTime dataHora = rs.getTimestamp("dataHora").toLocalDateTime();


				Curso curso = new Curso(idCurso, null, null, null, null, null);

				Modulo modulo = null;
				int moduloId = rs.getInt("modulo_id");
				if(!rs.wasNull()){
					modulo = new Modulo(moduloId, null, null, null);

				}
				Postagem postagem = new Postagem(titulo, autor, conteudo, id, dataHora, curso, modulo);
				postagens.add(postagem);
			}
		}

		return postagens;

	}
}