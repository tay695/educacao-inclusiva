<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ifbaiano.educacaoinclusiva.model.Tutor" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession sessao = request.getSession(false);
    Tutor tutor = (Tutor) sessao.getAttribute("usuarioLogado");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Portal tutor</title>
</head>
<body>
    <h1>Bem-vindo, <%= tutor.getRetornaNome() %>!</h1>
    <p>Área de especialização: <%= tutor.getAreaEspecializacao() %></p>

    <h2>Pesquise por algo...</h2>
    <ul>
        <li><a href="#">Postar novo vídeo</a></li>
        <li><a href="#">Ver cursos postados</a></li>
        <li><a href="#">Mensagens dos alunos</a></li>
        <li><a href="logout.jsp">Sair</a></li>
    </ul>
</body>
</html>
