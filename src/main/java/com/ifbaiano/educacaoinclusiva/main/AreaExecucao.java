package com.ifbaiano.educacaoinclusiva.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ifbaiano.educacaoinclusiva.DAO.AlunoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.CursoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.InscricaoDAO;
import com.ifbaiano.educacaoinclusiva.DAO.ModuloDAO;
import com.ifbaiano.educacaoinclusiva.DAO.TutorDAO;
import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.DAO.VideoAulaDAO;
import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import com.ifbaiano.educacaoinclusiva.model.Aluno;
import com.ifbaiano.educacaoinclusiva.model.Curso;
import com.ifbaiano.educacaoinclusiva.model.Inscricao;
import com.ifbaiano.educacaoinclusiva.model.Modulo;
import com.ifbaiano.educacaoinclusiva.model.Tutor;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaiano.educacaoinclusiva.model.VideoAula;
import com.ifbaiano.educacaoinclusiva.model.dto.SessionDTO;

public class AreaExecucao {

	private static String tipoUsuario;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		menu(scanner);
		scanner.close();
	}

	public static void menu(Scanner scanner) {
		try {
			Connection conexao = DBConfig.criarConexao();

			AlunoDAO alunoDAO = new AlunoDAO(conexao);
			TutorDAO tutorDAO = new TutorDAO(conexao);
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
			VideoAulaDAO videoDAO = new VideoAulaDAO(conexao);
			ModuloDAO moduloDAO = new ModuloDAO(conexao);
			VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conexao);
			InscricaoDAO inscricaoDAO = new InscricaoDAO(conexao);
			CursoDAO cursoDAO = new CursoDAO(conexao);
			int option;

			do {

				System.out.println("\n=============== Menu inicial ===============");
				System.out.println("1 - Cadastrar aluno");
				System.out.println("2 - Cadastrar tutor");
				System.out.println("3 - Realizar login");
				System.out.println("===============================================");
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
					tipoUsuario = scanner.nextLine();

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

						SessionDTO session = SessionDTO.getInstance();
						session.setUsuarioLogado(usuario);

						if (usuario.getTipoUsuario().equalsIgnoreCase("aluno")) {
							System.out.println("Você está logado como ALUNO.");
							Aluno aluno = alunoDAO.buscarPorIdUsuario(usuario.getId());
							if (aluno != null) {
								session.setUsuarioAluno(aluno);
							}

							System.out.println("Bem-vindo, " + usuario.getRetornaNome());
							menuAluno(scanner, conexao, usuario, videoAulaDAO);

						} else if (usuario.getTipoUsuario().equalsIgnoreCase("tutor")) {
							System.out.println("Você está logado como TUTOR.");

							Tutor tutor = tutorDAO.buscarPorIdUsuario(usuario.getId());
							if (tutor != null) {
								session.setUsuarioTutor(tutor);
								System.out.println("Bem-vindo, Tutor " + tutor.getRetornaNome());
								menuTutor(scanner, usuario);
							} else {
								System.out.println("Dados de tutor não encontrados!");
								menu(scanner);
							}
						}
					} else {
						System.out.println("Email ou senha inválidos. Tente novamente.");
						menu(scanner);
					}
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

	public static void menuTutor(Scanner scan, Usuario usuario) {
		try (Scanner scanner = new Scanner(System.in); Connection conexao = DBConfig.criarConexao()) {
			int option;
			ModuloDAO moduloDAO = new ModuloDAO(conexao);
			VideoAulaDAO videoAulaDAO = new VideoAulaDAO(conexao);
			CursoDAO cursoDAO = new CursoDAO(conexao);

			Modulo novoModulo = null;
			Curso novoCurso = null;

			do {
				System.out.println("\n=== olá " + usuario.getRetornaNome() + ",que bom que você está de volta. ===");
				System.out.println("1 - Ver perfil");
				System.out.println("2 - Adicionar um novo curso");
				System.out.println("3 - Adicionar nova video Aula");
				System.out.println("4 - Gerenciamento de cursos ");
				System.out.println("5 - Atualizar video aula ");
				System.out.println("6 - Sair da minha conta ");
				System.out.println("\n");
				option = scanner.nextInt();
				scanner.nextLine();

				switch (option) {
				case 1:
					System.out.println(" :) " + usuario.getRetornaNome() + " ;)");
					System.out.println("\n<-----> DADOS PESSOAIS <----->");
					System.out.println("Nome: " + usuario.getRetornaNome());
					System.out.println("Email: " + usuario.getEmail());
					System.out.println("Bio: " + usuario.getBio());
					break;

				case 2:
					try {
						System.out.println("----------------------------------------");
						System.out.println("<-----> Adicionar um novo curso <------>");
						System.out.println("----------------------------------------");

						System.out.print("\nTitulo do curso: ");
						String tituloCurso = scanner.nextLine();
						System.out.print("\nDescrição do Curso: ");
						String descricaoCurso = scanner.nextLine();
						System.out.print("\nQual a área de atuação do curso: ");
						String areaCurso = scanner.nextLine();

						SessionDTO session = SessionDTO.getInstance();
						Tutor tutorLogado = session.getTutorLogado();

						if (tutorLogado == null) {
							System.out.println("Erro: Tutor não autenticado!");
							break;
						}
						
						Curso novoCurso1 = new Curso();
						novoCurso1.setTitulo(tituloCurso);
						novoCurso1.setDescricao(descricaoCurso);
						novoCurso1.setArea(areaCurso);

						novoCurso1.setTutorId(tutorLogado.getIdUsuario());
						Curso cursoCriado = cursoDAO.addCurso(novoCurso1);

						if (cursoCriado == null || cursoCriado.getId() == 0) {
							System.out.println("Falha ao criar o curso!");
							break;
						}
						System.out.println("Curso criado com sucesso!");

						// Parte do modulo
						System.out.println("\n--- Adicionar primeiro módulo ao curso ---");
						System.out.print("\nTítulo do módulo: ");
						String tituloModulo = scanner.nextLine();

						System.out.print("\nDescrição do módulo: ");
						String descricaoModulo = scanner.nextLine();

						Modulo novoModulo1 = new Modulo();
						novoModulo1.setTitulo(tituloModulo);
						novoModulo1.setDescricao(descricaoModulo);
						novoModulo1.setIdCurso(cursoCriado.getId());

						moduloDAO.inserirModulo(novoModulo1);

						System.out.println("Módulo criado com sucesso! ");

					} catch (Exception e) {
						System.err.println("\nErro ao cadastrar curso e módulo: " + e.getMessage());
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("----------------------------------------");
					System.out.println("<-----> Adicionar video aula  <------>");
					System.out.println("----------------------------------------");
					 try {
						 System.out.println("\nMódulos disponíveis:");
					        List<Modulo> modulos = moduloDAO.listarTodosModulos();
					        
					        if (modulos.isEmpty()) {
					            System.out.println("Nenhum módulo cadastrado ainda. Cadastre um curso e módulo primeiro.");
					            break;
					        }
					for (int i = 0; i < modulos.size(); i++) {
						 Modulo modulo = modulos.get(i);
				         Curso curso = cursoDAO.buscarPorId(modulo.getIdCurso()); 
				         System.out.println((i + 1) + " - " + modulo.getTitulo());
						break;
					}

					  System.out.print("\nEm qual módulo deseja adicionar aulas? ");
				        int escolhaM;
				        
				            escolhaM = Integer.parseInt(scanner.nextLine());
				            Modulo moduloEscolhido = modulos.get(escolhaM - 1);
				        
				        Curso curso = cursoDAO.buscarPorId(moduloEscolhido.getIdCurso());
				       
				        System.out.print("\nTítulo da videoaula: ");
				        String tituloVideo = scanner.nextLine();

				        System.out.print("\nURL do vídeo: ");
				        String urlVideo = scanner.nextLine();

				        VideoAula novaVideoAula = new VideoAula();
				        novaVideoAula.setTitulo(tituloVideo);
				        novaVideoAula.setUrl(urlVideo);
				        novaVideoAula.setmoduloId(moduloEscolhido.getId());

				        videoAulaDAO.inserirVideoaula(novaVideoAula);
				        System.out.println("\n Vídeo aula cadastrada com sucesso!");
	
				    } catch (SQLException e) {
				        e.printStackTrace();
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				    break;
				case 4:
					System.out.println("------------------------------------------");
					System.out.println("\n<-----> Painel de Gerenciamento <------>");

					try {
						SessionDTO session = SessionDTO.getInstance();

						if (!session.isLoggedIn()) {
							System.out.println("Usuário não autenticado! Faça login primeiro.");
							break;
						}

						Tutor tutor = session.getTutorLogado();
						if (tutor == null) {
							if ("tutor".equalsIgnoreCase(session.getTipo())) {
								System.out.println("Dados de tutor não carregados! Tentando recarregar...");

								TutorDAO tutorDAO = new TutorDAO(conexao);
								tutor = tutorDAO.buscarPorIdUsuario(session.getId());

								if (tutor != null) {
									session.setUsuarioTutor(tutor);
									System.out.println("Dados do tutor recarregados com sucesso!");
								} else {
									System.out.println("Erro: Perfil de tutor não encontrado.");
									System.out.println("Por favor, complete seu cadastro como tutor.");
									break;
								}
							} else {
								System.out.println("Acesso restrito a tutores!");
								break;
							}
						}

						System.out.println("Buscando cursos para o tutor ID: " + tutor.getIdUsuario());

						List<Curso> cursosDoTutor = cursoDAO.listarPorTutor(tutor.getIdUsuario());

						if (cursosDoTutor.isEmpty()) {
							System.out.println("Nenhum curso encontrado para este tutor.");
							break;
						}

						for (Curso curso : cursosDoTutor) {
							System.out.println("\nCurso: " + curso.getTitulo());

							List<Modulo> modulosDoCurso = moduloDAO.listarPorCurso(curso.getId());

							if (modulosDoCurso.isEmpty()) {
								System.out.println("   |-- Nenhum módulo no curso.");
								continue;
							}

							for (Modulo modulo : modulosDoCurso) {
								System.out.println("   |-- Módulo: " + modulo.getTitulo());

								List<VideoAula> videosDoModulo = videoAulaDAO.buscarPorModulo(modulo.getId());

								if (videosDoModulo.isEmpty()) {
									System.out.println("       |-- Nenhum vídeo encontrado.");
									continue;
								}

								for (VideoAula video : videosDoModulo) {
									System.out.println("       |-- Vídeo: " + video.getTitulo());
								}
							}
						}
					} catch (SQLException e) {
						System.err.println("\nErro ao acessar o banco de dados: " + e.getMessage());
						e.printStackTrace();
					} catch (Exception e) {
						System.err.println("\nErro inesperado: " + e.getMessage());
						e.printStackTrace();
					}
					break;
				case 5:
					System.out.println("==== ATUALIZE SUAS AULAS ====");
					List<VideoAula> aulas = videoAulaDAO.listarPorAulas();
					if (aulas.isEmpty()) {
						System.out.println("Não ha aulas cadastradas");
					}

					for (int i = 0; i < aulas.size(); i++) {
						VideoAula nova = aulas.get(i);
						System.out.println((i + 1) + " - " + nova.getTitulo());
					}

					System.out.println("\nQual video deseja atualizar?");
					int escolha = scanner.nextInt();
					scanner.nextLine();

					VideoAula aulaSelecionada = aulas.get(escolha - 1);
					System.out.print("Novo título da videoaula: ");
					String novoTitulo = scanner.nextLine();

					System.out.print("Nova URL do vídeo: ");
					String novaUrl = scanner.nextLine();

					aulaSelecionada.setTitulo(novoTitulo);
					aulaSelecionada.setUrl(novaUrl);

					try {
						videoAulaDAO.atualizarVideoaula(aulaSelecionada);
						System.out.println("Aula atualizada com sucesso!");
					} catch (SQLException e) {
						System.err.println("Erro ao atualizar a aula: " + e.getMessage());
						e.printStackTrace();
					}

					break;
				case 6:
					System.out.println("Encerrando sessão... :(");
					menu(scanner);
					break;
				default:
					System.out.println("Opção inválida.");
				}
			} while (option != 6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void menuAluno(Scanner scanner, Connection conexao, Usuario usuario, VideoAulaDAO videoAulaDAO) {
		int option = 0;
		do {
			System.out.println("\n=== MENU DO ALUNO ===");
			System.out.println("1 - Ver perfil");
			System.out.println("2 - Inscrever em vídeo aula");
			System.out.println("3 - Ver vídeos aulas disponíveis");
			System.out.println("4 - Ver minhas inscrições");
			System.out.println("5 - Sair");
			System.out.print("Escolha: ");

			String escolha = scanner.nextLine();
			try {
				option = Integer.parseInt(escolha);
			} catch (NumberFormatException e) {
				System.out.println("Opção inválida. Digite um número.");
				continue;
			}

			switch (option) {
			case 1:
				System.out.println("\n Nome: " + usuario.getRetornaNome());
				System.out.println("Email: " + usuario.getEmail());
				System.out.println("Bio: " + usuario.getBio());
				break;

			case 2:
				try {
					ModuloDAO moduloDAO = new ModuloDAO(conexao);
					InscricaoDAO inscricaoDAO = new InscricaoDAO(conexao);
					List<Modulo> modulos = moduloDAO.listarModulosComVideos();

					if (modulos.isEmpty()) {
						System.out.println("Nenhum módulo com vídeos disponível.");
						break;
					}

					int contador = 1;
					List<VideoAula> todasAulas = new ArrayList<>();

					for (Modulo modulo : modulos) {
						System.out.println("Módulo: " + modulo.getTitulo());
						for (VideoAula v : modulo.getVideoAulas()) {
							System.out.println(" " + contador + " - " + v.getTitulo() + " (" + v.getUrl() + ")");
							todasAulas.add(v);
							contador++;
						}
					}

					System.out.print("Digite o número da vídeo aula que deseja se inscrever: ");
					String escolhaNumero = scanner.nextLine();
					int indice = Integer.parseInt(escolhaNumero) - 1;

					if (indice >= 0 && indice < todasAulas.size()) {
						VideoAula selecionada = todasAulas.get(indice);
						InscricaoDAO inscricaoDAO2 = new InscricaoDAO(conexao);
						Inscricao inscricao = new Inscricao(usuario.getId(), selecionada.getId());

						if (inscricaoDAO2.jaInscrito(usuario.getId(), selecionada.getId())) {
							System.out.println("Você já está inscrito nesta vídeo aula.");
						} else {
							inscricaoDAO2.inscreverEmVideoAula(inscricao);
							System.out.println("Inscrição realizada com sucesso!!");
							System.out.println("Você se inscreveu na vídeo aula:");
							System.out.println("  - " + selecionada.getTitulo() + " (" + selecionada.getUrl() + ")");
						}
					} else {
						System.out.println("Número inválido. Tente novamente.");
					}
				} catch (NumberFormatException | SQLException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;

			case 3:
				ModuloDAO moduloDAO3 = new ModuloDAO(conexao);
				List<Modulo> modulos3 = moduloDAO3.listarModulosComVideos();

				if (modulos3.isEmpty()) {
					System.out.println("Nenhum módulo disponível no momento");
				} else {
					for (Modulo modulo : modulos3) {
						System.out.println("Modulo: " + modulo.getTitulo());
						for (VideoAula video : modulo.getVideoAulas()) {
							System.out.println("    - Vídeo: " + video.getTitulo() + " (" + video.getUrl() + ")");
						}
					}
				}
				break;

			case 4:
				try {
					InscricaoDAO inscricaoDAO4 = new InscricaoDAO(conexao);
					List<VideoAula> inscricoes = inscricaoDAO4.listarInscricoesPorUsuario(usuario.getId());

					if (inscricoes.isEmpty()) {
						System.out.println("Você ainda não se inscreveu em nenhuma vídeo aula.");
					} else {
						System.out.println("Suas inscrições:");
						for (VideoAula v : inscricoes) {
							System.out.println(" - " + v.getTitulo() + " (" + v.getUrl() + ")");
						}
					}
				} catch (SQLException e) {
					System.out.println("Erro ao buscar inscrições: " + e.getMessage());
				}
				break;

			case 5:
				System.out.println("Saindo do menu de aluno...");
				menu(scanner);
				break;

			default:
				System.out.println("Opção inválida.");
			}
		} while (option != 5);
	}
}