package com.ifbaiano.educacaoinclusiva.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifbaiano.educacaoinclusiva.DAO.UsuarioDAO;
import com.ifbaiano.educacaoinclusiva.model.Usuario;
import com.ifbaino.educacaoinclusiva.utils.SenhaUtils;
import com.ifbaino.educacaoinclusiva.utils.validation.ErroCampo;
import com.ifbaino.educacaoinclusiva.utils.validation.Validador;

public class LoginController {

    private final UsuarioDAO usuarioDao;

    public LoginController(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public List<ErroCampo> autenticar(String email, String senhaDigitada) throws SQLException {
        List<ErroCampo> erros = new ArrayList<>();

        // validando os campos 
        
        Validador.notBlank(email, "email", erros);
        Validador.notBlank(senhaDigitada, "senha", erros);

        if (!erros.isEmpty()) return erros;

        // fazendo a busca do usuário 
        
        Usuario usuario = usuarioDao.buscarEmail(email);

        if (usuario == null) {
            erros.add(new ErroCampo("email", email, "Email não encontrado"));
            return erros;
        }

        // Verificando a senha
        boolean senhaValida = SenhaUtils.verificarSenha(senhaDigitada, usuario.getSalt(), usuario.getSenha());
        if (!senhaValida) {
            erros.add(new ErroCampo("senha", senhaDigitada, "Senha incorreta"));
        }
        return erros;
    }
}
