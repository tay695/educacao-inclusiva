package com.ifbaiano.educacaoinclusiva.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.CursoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Curso;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;

public class AreaExecucao {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			Connection conexao = DBConfig.criarConexao();

			AlunoDAO alunoDAO = new AlunoDAO(conexao);
			TutorDAO tutorDAO = new TutorDAO(conexao);
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			VideoAulaDAO videoDAO = new VideoAulaDAO(conexao);
			ModuloDAO moduloDAO1 = new ModuloDAO(conexao);

			int option;

			do {
				System.out.println("\n=== MENU ===");
				System.out.println("1 - Cadastrar aluno");
				System.out.println("2 - Cadastrar tutor");
				System.out.println("3 - Realizar login");
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
		try (Scanner scanner = new Scanner(System.in); Connection conexao = DBConfig.criarConexao()) {
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
			} while (option != 4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void menuTutor(Scanner scan, Usuario usuario) {
	    try (Scanner scanner = new Scanner(System.in); Connection conexao = DBConfig.criarConexao()) {
	        int option;
	        ModuloDAO moduloDAO = new ModuloDAO(conexao);
	        VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conexao);
	        CursoDAO cursoDAO = new CursoDAO(conexao);

	        Modulo novoModulo = null; 
	        Curso novoCurso = null;

	        do {
	            System.out.println("\n=== MENU DO TUTOR ===");
	            System.out.println("1 - Ver perfil");
	            System.out.println("2 - Adicionar um novo curso");
	            System.out.println("3 - Adicionar nova video Aula");
	            System.out.println("4 - Gerenciamento de cursos ");
	            System.out.println("5 - Sair da minha conta ");
	            option = scanner.nextInt();
	            scanner.nextLine();

	            switch (option) {
	                case 1:
	                    System.out.println("==== " + usuario.getRetornaNome() + " ====");
	                    System.out.println("\n------------------------------");
	                    System.out.println("\n<-----> DADOS PESSOAIS <----->");
	                    System.out.println("\n------------------------------");
	                    System.out.println("Nome: " + usuario.getRetornaNome());
	                    System.out.println("Email: " + usuario.getEmail());
	                    System.out.println("Bio: " + usuario.getBio());
	                    break;

	                case 2:
	                    try {
	                        System.out.println("\n-------------------------------------");
	                        System.out.println("<-----> Adicionar um novo curso <------>");
	                        System.out.println("---------------------------------------");

	                        System.out.print("Titulo do curso: ");
	                        String tituloCurso = scanner.nextLine();
	                        System.out.print("Descrição do Curso: ");
	                        String descricaoCurso = scanner.nextLine();
	                        System.out.print("Qual a área de atuação do curso: ");
	                        String areaCurso = scanner.nextLine();

	                        novoCurso = new Curso();
	                        novoCurso.setTitulo(tituloCurso);
	                        novoCurso.setDescricao(descricaoCurso);
	                        novoCurso.setArea(areaCurso);

	                        cursoDAO.addCurso(novoCurso);

	                        System.out.println("Curso criado com sucesso! ID: " + novoCurso.getId());

	                        System.out.print("Título do módulo: ");
	                        String tituloModulo = scanner.nextLine();
	                        System.out.print("Descrição do módulo: ");
	                        String descricaoModulo = scanner.nextLine();

	                        novoModulo = new Modulo();
	                        novoModulo.setTitulo(tituloModulo);
	                        novoModulo.setDescricao(descricaoModulo);
	                        novoModulo.setIdCurso(novoCurso.getId());

	                        moduloDAO.inserirModulo(novoModulo);

	                        System.out.println("Módulo criado com sucesso! ID: " + novoModulo.getId());

	                    } catch (Exception e) {
	                        System.out.println("Erro ao cadastrar curso e módulo: " + e.getMessage());
	                    }
	                    break;

	                case 3:
	                    if (novoModulo == null) {
	                        System.out.println("Nenhum módulo foi criado ainda. Cadastre um curso e módulo primeiro.");
	                        break;
	                    }

	                    System.out.println("\n--------------------------------------");
	                    System.out.println("\n<-----> Adicionar video aula  <------>");
	                    System.out.println("\n--------------------------------------");

	                    System.out.print("Título da videoaula: ");
	                    String tituloVideo = scanner.nextLine();

	                    System.out.print("URL do vídeo (ex: https://...): ");
	                    String urlVideo = scanner.nextLine();

	                    int idModulo = novoModulo.getId();

	                    VideoAula video = new VideoAula();
	                    video.setTitulo(tituloVideo);
	                    video.setUrl(urlVideo);
	                    video.setmoduloId(idModulo); 
	                    try {
	                        videoAulaDAO.inserirVideoaula(video);
	                        System.out.println("Vídeo aula cadastrada com sucesso!");
	                    } catch (SQLException e) {
	                        System.err.println("Erro ao cadastrar vídeo aula: " + e.getMessage());
	                        e.printStackTrace();
	                    }

	                    break;

	                case 4:
	                    System.out.println("\n----------------------------------------");
	                    System.out.println("\n<-----> Painel de Gerenciamento <------>");
	                    System.out.println("\n----------------------------------------");
	                    List<Modulo> modulos = moduloDAO.listarModulosComVideos();
	                    List<VideoAula> videoaulas = videoAulaDAO.listarPorAulas();

	                    for (Modulo m : modulos) {
	                        System.out.println("Módulo: " + m.getTitulo());
	                        for (VideoAula v : m.getVideoAulas()) {
	                            System.out.println("   - Vídeo: " + v.getTitulo() + " (" + v.getUrl() + ")");
	                        }
	                    }
	                    break;
	                case 5: 
	                	System.out.println("Encerrando sessão...");
	                default:
	                    System.out.println("Opção inválida.");
	            }
	        } while (option != 5);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
