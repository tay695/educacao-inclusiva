package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Postagem;


public class PostagemDAO{
	private Connection conexao;

	public PostagemDAO(){
		conexao = DBConfig.criarConexao();
	}
	public void inserir(Postagem postagem) throws SQLException{
		String sql = "INSERT INTO Postagem (titulo, autor, conteudo, dataHora) VALUES (?, ?, ?, ?)";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
		stmt.setString(1, postagem.getTitulo());
		stmt.setString(1, postagem.getAutor());
		stmt.setString(3, postagem.getConteudo());
		stmt.setTimestamp(4, Timestamp.valueOf(postagem.getDataHora()));
		stmt.executeUpdate();
	}
}
	public List<Postagem> listarTodas() throws SQLException{
		List<Postagem> postagens = new ArrayList<>();
		String sql = "SELECT * FROM Postagem";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			Postagem p = new Postagem(
				rs.getString("titulo"),
				rs.getString("autor"),
				rs.getString("conteudo"),
				rs.getInt("id"),
				rs.getTimestamp("dataHora").toLocalDateTime()
			);
		postagens.add(p);
	}
}
	return postagens;
	}

		public Postagem buscarPorId(int id) throws SQLException{
			String sql = "SELECT  * FROM Postagem WHERE id = ?";

				try(PreparedStatement stmt = conexao.prepareStatement(sql)){
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();

					if(rs.next()){
						return new Postagem(
							rs.getString("titulo"),
							rs.getString("autor"),
							rs.getString("conteudo"),
							rs.getInt("id"),
							rs.getTimestamp("dataHora").toLocalDateTime()
						);
						}
					}
				return null;


	}		
}