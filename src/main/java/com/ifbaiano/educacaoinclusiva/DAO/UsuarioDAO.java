package com.ifbaiano.educacaoinclusiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class UsuarioDAO {
	private Connection conexao;

	public UsuarioDAO() {
		this.conexao = DBConfig.criarConexao();
	}

	public int inserir(Usuario usuario) throws SQLException {

		String sql = "INSERT INTO Usuario (nome,email,senha,bio,salt) VALUES (?,?,?,?)";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, usuario.getRetornaNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getBio());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idGerado = rs.getInt(1);
				usuario.setId(idGerado); // captura o id
				return idGerado;
			}
		}
		return -1;
	}

	public Usuario buscarEmail(String email) throws SQLException {
		String sql = "SELECT * from Usuario  WHERE email = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet resul = stmt.executeQuery();

			if (resul.next()) {
				String nome = resul.getString("nome");
				String emailAluno = resul.getString("email");
				return new Aluno(0, nome, emailAluno, null, null,null);

			}
		}
		return null;
	}

	public Usuario buscarId(int id) throws SQLException {
		String sql = "SELECT nome,id from Usuario  WHERE id = ?";

		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet resul = stmt.executeQuery();

			if (resul.next()) {
				return new Usuario(resul.getInt("id"), resul.getString("nome"), null, null, null);
			}
		}
		return null;
	}
	public void salvar(Usuario usuario) throws SQLException {
        String sql;

        if (usuario instanceof Aluno) {
            sql = "INSERT INTO aluno (nome, email, senha, bio, data_nascimento) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                Aluno aluno = (Aluno) usuario; // CAST para acessar os dados de alunos 
                stmt.setString(1, aluno.getRetornaNome());
                stmt.setString(2, aluno.getEmail());
                stmt.setString(3, aluno.getSenha());
                stmt.setString(4, aluno.getBio());
                stmt.setDate(5, aluno.getDataNascimento()); 
                stmt.executeUpdate();
            }
        } else if (usuario instanceof Tutor) {
            sql = "INSERT INTO tutor (nome, email, senha, bio, area_especializacao) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, usuario.getRetornaNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.setString(4, usuario.getBio());
                stmt.setString(5, ((Tutor) usuario).getAreaEspecializacao());
                stmt.executeUpdate();
            }
        }
    }

}
