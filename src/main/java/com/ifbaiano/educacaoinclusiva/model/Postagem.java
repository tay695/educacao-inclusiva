package com.ifbaiano.educacaoinclusiva.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Postagem{
    private String titulo;
    private String autor;
    private String conteudo;
    private int id;
    private LocalDateTime dataHora;
    private Curso curso; 
    private Modulo modulo;
    private List<String> comentarios = new ArrayList<>();

    public Postagem(String titulo, String autor, String conteudo, int id, LocalDateTime dataHora, Curso curso, Modulo modulo){
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
        this.id = id;
        this.dataHora = dataHora;
        this.curso = curso;
        this.modulo = modulo;
    }   
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getConteudo(){
        return conteudo;
    }
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDataHora(){
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }
    public Curso getCurso(){
        return curso;
    }
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    public Modulo getModulo() {
         return modulo; 
    }
    public void setModulo(Modulo modulo) {
        this.modulo = modulo; 
    }
    public List<String> getComentarios(){
        return comentarios;
    }
    public void adicionarComentario (String comentario){
        comentarios.add(comentario);
    }
}

/* CLASSE MAIN
 * import java.time.LocalDataTIme;
 * import java.util.Scanner;
 * 
 * public class Main{
 * 
 * public static void main{
 * Scanner scanner = new Scanner(System.in);
 * Postagem postagem = null;
 * 
 * while(true){
 *      System.out.println("Escolha uma opção:");
            System.out.println("1. Criar nova postagem");
            System.out.println("2. Adicionar comentário");
            System.out.println("3. Exibir postagens");
            System.out.println("4. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

             case 1:
                    // Criar uma nova postagem
                    System.out.print("Digite o título da postagem: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor da postagem: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o conteúdo da postagem: ");
                    String conteudo = scanner.nextLine();
                    LocalDataTime dataHora = LocalDataTime.now();

                    postagem = new Postagem(titulo, autor, conteudo, 1, dataHora);
                    System.out.println("Postagem criada com sucesso!!");
                    break;

                    case 2:
                    // Adicionar um comentário a uma postagem existente
                    if (postagem != null) {
                        System.out.print("Digite seu comentário: ");
                        String comentario = scanner.nextLine();
                        postagem.adicionarComentario(comentario);
                        System.out.println("Comentário adicionado com sucesso!");
                    } else {
                        System.out.println("Você precisa criar uma postagem primeiro.");
                    }
                    break;

                    case 3:
                    // Exibir postagens e seus comentários
                    if (postagem != null) {
                        System.out.println("\nPostagem:");
                        System.out.println("Título: " + postagem.getTitulo());
                        System.out.println("Autor: " + postagem.getAutor());
                        System.out.println("Conteúdo: " + postagem.getConteudo());
                        System.out.println("Data e Hora: " + postagem.getDataHora());
                        System.out.println("Comentários:");
                        for (String comentario : postagem.getComentarios()) {
                            System.out.println("- " + comentario);
                        }
                    } else {
                        System.out.println("Nenhuma postagem foi criada ainda.");
                    }
                    break;

                     case 4:
                    // Sair
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                    default:
                    System.out.println("Opção inválida. Tente novamente.");
 *  
 *              }
 *          }
 *      }
 *  }
*/