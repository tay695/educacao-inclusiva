package com.ifbaiano.educacaoinclusiva.view;

import com.ifbaiano.educacaoinclusiva.config.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Cadastro {

    public static void centralizar(JFrame janela) {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (tela.width - janela.getWidth()) / 2;
        int y = (tela.height - janela.getHeight()) / 2;
        janela.setLocation(x, y);
    }

    public static boolean cadastrar(String nome, String senha, String email) {
        String sql = "INSERT INTO Usuario (nome, senha, email) VALUES (?, ?, ?)";

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = DBConfig.criarConexao();
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, email);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void cadastrarPage() {
        JFrame janela = new JFrame("Cadastro");
        JButton botaoCadastrar = new JButton("Cadastrar-se");
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField campoNome = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField campoEmail = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        JLabel cadastrar = new JLabel("Já tem uma conta? Clique aqui para logar-se");

        botaoCadastrar.setBounds(140, 380, 120, 40);
        nomeLabel.setBounds(140, 70, 120, 40);
        campoNome.setBounds(140, 100, 120, 40);
        emailLabel.setBounds(140, 170, 120, 40);
        campoEmail.setBounds(140, 200, 120, 40);
        senhaLabel.setBounds(140, 270, 120, 40);
        campoSenha.setBounds(140, 300, 120, 40);
        cadastrar.setBounds(80, 410, 300, 40);
        cadastrar.setForeground(Color.BLUE);
        cadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String email = campoEmail.getText();
                String senha = new String(campoSenha.getPassword());

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                } else {
                    boolean sucesso = cadastrar(nome, senha, email);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Usuário " + nome + " cadastrado com sucesso!");
                        campoNome.setText("");
                        campoEmail.setText("");
                        campoSenha.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar. Verifique o console.");
                    }
                }
            }
        });

        cadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                janela.dispose();
                new LoginPage().logar();
            }
        });

        janela.add(botaoCadastrar);
        janela.add(nomeLabel);
        janela.add(campoNome);
        janela.add(emailLabel);
        janela.add(campoEmail);
        janela.add(senhaLabel);
        janela.add(campoSenha);
        janela.add(cadastrar);
        janela.setLayout(null);

        janela.setSize(400, 600);
        centralizar(janela);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}