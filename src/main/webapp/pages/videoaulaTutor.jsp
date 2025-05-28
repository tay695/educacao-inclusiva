<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%

    //BLOCO DE TESTE (REMOVER)
    HttpSession sessao = request.getSession();

    // Limpa usuário antigo
    sessao.removeAttribute("usuarioLogado");
    
    if(sessao.getAttribute("usuarioLogado") == null){
        com.ifbaiano.educacaoinclusiva.model.Tutor usuarioTeste = new com.ifbaiano.educacaoinclusiva.model.Tutor(
            "Matemática", 1, "Teste Tutor", "tutor@teste.com", "123456", "Biografia de Teste"
        );

        sessao.setAttribute("usuarioLogado", usuarioTeste);
    }
    //FIM DO BLOCO DE TESTE

%>

<%
    Tutor tutor = (Tutor) sessao.getAttribute("usuarioLogado");
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Painel de Vídeo Aulas</title>
</head>
<body>

<h1>Seja Bem-vindo, <%= tutor.getRetornaNome() %>!</h1>
<p>Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

<h2>Menu de Gerenciamento de Vídeo Aulas</h2>
<ul>
    <li><a href="formularioCadastroVideoAula.jsp">Postar nova video aula</a></li>
    <li><a href="listarvideoaulaTutor.jsp">Visualizar todas as video aulas</a></li>
    <li><a href="logout.jsp">Sair</a></li>
</ul>

</body>
</html>
