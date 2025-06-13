package com.ifbaiano.educacaoinclusiva.main;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;

public class AreaExecucao {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			Connection conexao = DBConfig.criarConexao();

			AlunoDAO alunoDAO = new AlunoDAO(conexao);
			TutorDAO tutorDAO = new TutorDAO(conexao);
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

			int option;

			do {
				System.out.println("\n=== MENU ===");
				System.out.println("1 - Cadastrar aluno");
				System.out.println("2 - Cadastrar tutor");
				System.out.println("3 - Realizar login");
				System.out.print("Escolha: ");
				option = scanner.nextInt();
				scanner.nextLine();

				switch (option) {
					case 1: {
						System.out.println("Nome:");
						String nome = scanner.nextLine();
						System.out.print("Email: ");
						String email = scanner.nextLine();
						System.out.print("Senha: ");
						String senha = scanner.nextLine();
						System.out.println("Bio:");
						String bio = scanner.nextLine();
						System.out.println("Tipo de usuário:");
						String tipoUsuario = scanner.nextLine();

						Usuario novoUsuario = new Usuario(nome, email, senha, bio, "aluno");
						int idGerado = usuarioDAO.inserir(novoUsuario);
						System.out.println("ID do usuário gerado: " + idGerado);

						if (idGerado != -1) {
							Aluno aluno = new Aluno(nome, email, senha, bio, "aluno");
							aluno.setIdUsuario(idGerado);
							alunoDAO.inserirAluno(aluno);
							System.out.println("Aluno cadastrado com sucesso.");
						} else {
							System.out.println("Erro ao cadastrar usuário.");
						}
						break;
					}

					case 2: {
						System.out.println("Área de especialização:");
						String areaEspecializacao = scanner.nextLine();
						System.out.println("Nome:");
						String nome = scanner.nextLine();
						System.out.print("Email: ");
						String email = scanner.nextLine();
						System.out.print("Senha: ");
						String senha = scanner.nextLine();
						System.out.println("Bio:");
						String bio = scanner.nextLine();
						System.out.println("Tipo de usuário:");
						String tipoUsuario = scanner.nextLine();

						Usuario novoUsuario = new Usuario(nome, email, senha, bio, tipoUsuario);
						int idGerado = usuarioDAO.inserir(novoUsuario);

						if (idGerado != -1) {
							Tutor tutor = new Tutor(nome, email, senha, bio, tipoUsuario, areaEspecializacao);
							tutor.setIdUsuario(idGerado);
							tutorDAO.inserirTutor(tutor);
							System.out.println("Tutor cadastrado com sucesso.");
						} else {
							System.out.println("Erro ao cadastrar usuário.");
						}
						break;
					}

					case 3: {
						System.out.println("Digite seu email:");
						String email = scanner.nextLine();
						System.out.println("Digite sua senha:");
						String senha = scanner.nextLine();

						Usuario usuario = usuarioDAO.buscarEmail(email);

						if (usuario != null && usuario.getSenha().equals(senha)) {
							System.out.println("Login realizado com sucesso!");

							if (usuario.getTipoUsuario().equalsIgnoreCase("aluno")) {
								System.out.println("Você está logado como ALUNO.");
								menuAluno(scanner, usuario);
								System.out.println("Bem-vindo, " + usuario.getRetornaNome());

							} else if (usuario.getTipoUsuario().equalsIgnoreCase("tutor")) {
								System.out.println("Você está logado como TUTOR.");
								menuTutor(scanner, usuario);
							}

						} else {
							System.out.println("Email ou senha inválidos. Tente novamente.");
						}
						break;
					}

					case 4: {
						System.out.println("Logout realizado");
						break;
					}

					default: {
						System.out.println("Opção inválida");
						break;
					}
				}
			} while (option != 3);

			conexao.close();

		} catch (SQLException e) {
			System.out.println("Erro ao conectar com o banco: " + e.getMessage());
		}

		scanner.close();
	}


public static void menuAluno(Scanner scan, Usuario usuario) {
	try (Scanner scanner = new Scanner(System.in);
			Connection conexao = DBConfig.criarConexao()) {
		int option;
	do {
		System.out.println("\n=== MENU DO ALUNO ===");
		System.out.println("1 - Ver perfil");
		System.out.println("2 - Inscrver em video aula");
		System.out.println("3 - Ver vídeos aulas disponíveis");
		System.out.println("4 - Sair");
		option = scanner.nextInt();
		scanner.nextLine();

		switch (option) {
			case 1:
				System.out.println("Nome: " + usuario.getRetornaNome());
				System.out.println("Email: " + usuario.getEmail());
				System.out.println("Bio: " + usuario.getBio());
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("Opção inválida.");
		}
	} while (option != 3);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}



public static void menuTutor(Scanner scan, Usuario usuario) {
	try (Scanner scanner = new Scanner(System.in);
			Connection conexao = DBConfig.criarConexao()) {
		int option;
		do {
			System.out.println("\n=== MENU DO TUTOR ===");
			System.out.println("1 - Ver perfil");
			System.out.println("2 - Cadastra novo módulo");
			System.out.println("3 - Ver minhas aulas postadas");
			System.out.println("4 - Sair da minha conta ");
			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					System.out.println("==== " + usuario.getRetornaNome() + " ====" );
					System.out.println("\n------------------------------");
					System.out.println("\n<-----> DADOS PESSOAIS <----->");
					System.out.println("\n------------------------------");
					System.out.println("Nome: " + usuario.getRetornaNome());
					System.out.println("Email: " + usuario.getEmail());
					System.out.println("Bio: " + usuario.getBio());
					break;
				case 2:
					System.out.println("\n-------------------------------------");
					System.out.println("\n<-----> CADASTRAR NOVO MÓDULO <----->");
					System.out.println("\n-------------------------------------");				
					break;
				case 3:
					System.out.println("\n--------------------------------------");
					System.out.println("\n<-----> Gerenciamento de aulas <----->");
					System.out.println("\n--------------------------------------");				
					break;
				case 4:
					break;
				default:
					System.out.println("Opção inválida.");
			}
		} while (option != 3);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


}

